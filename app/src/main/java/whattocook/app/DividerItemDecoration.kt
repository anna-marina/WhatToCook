package whattocook.app

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View

class DividerItemDecoration : RecyclerView.ItemDecoration {

    private val mDivider: Drawable

    constructor(context: Context) {
        val styledAttributes = context.obtainStyledAttributes(ATTRS)
        mDivider = styledAttributes.getDrawable(0)
        styledAttributes.recycle()
    }

    /**
     * Custom divider will be used
     */
    constructor(context: Context, resId: Int) {
        mDivider = ContextCompat.getDrawable(context, resId)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        var left = parent.paddingLeft
        var right = parent.width - parent.paddingRight

        left += 30
        right -= 30

        val childCount = parent.childCount
        for (i in 0..childCount - 1) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider.intrinsicHeight

            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)

            /*
            int diff = 10;
            int cur = top + diff;
            boolean dir = true;
            Paint p = new Paint();
            p.setColor(Color.LTGRAY);
            p.setStrokeWidth(2);
            for (int j = left; j < right; j += diff) {
                int next = cur;
                if (dir) {
                    next -= diff;
                    dir = false;
                } else {
                    next += diff;
                    dir = true;
                }

                c.drawLine(j, cur, j + diff, next, p);
                cur = next;
            }
            */
        }
    }

    companion object {

        private val ATTRS = intArrayOf(android.R.attr.listDivider)
    }
}
