package whattocook.app

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

import java.util.Random

class CheckView(c: Context, a: AttributeSet) : View(c, a) {

    var animationState = 1.0f
    var checked = false

    override fun onDraw(c: Canvas) {
        super.onDraw(c)

        val w = width.toFloat()
        val h = height.toFloat()

        val p = Paint()
        p.flags = Paint.ANTI_ALIAS_FLAG

        /* Changes in Android M */
        p.color =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    ContextCompat.getColor(context, R.color.check)
                else
                    context.getResources().getColor(R.color.check)
        p.style = Paint.Style.STROKE
        p.strokeWidth = 3.0f

        val sx = w / 2.0f
        val sy = h / 2.0f

        val a = animationState + (if (checked) 0.0f else 1.0f)
        val t = if (checked) animationState else 1.0 - animationState

        val ax = sx + Math.cos(a * Math.PI / 2.0).toFloat() * sx
        val ay = sy + Math.sin(a * Math.PI / 2.0).toFloat() * sy

        val bx = sx - Math.cos(a * Math.PI / 2.0).toFloat() * sx
        val by = sy - Math.sin(a * Math.PI / 2.0).toFloat() * sy
        c.drawLine(ax, ay, bx, by, p)

        val cx = sx + Math.cos(a * Math.PI).toFloat() * sx
        val cy = sy + Math.sin(a * Math.PI).toFloat() * sy

        val dx = sx - Math.cos(a * Math.PI).toFloat() * sx
        val dy = sy - Math.sin(a * Math.PI).toFloat() * sy
        c.drawLine(cx, cy, dx, dy, p)
    }

    fun toggle() {
        val va = ValueAnimator.ofFloat(0.0f, 1.0f)
        checked = !checked
        va.setDuration(300)
        va.addUpdateListener(CheckAnimation(this));
        va.start()
    }

    fun set() {
        checked = true
    }

    fun unset() {
        checked = false
    }

}

class CheckAnimation(val c: CheckView): ValueAnimator.AnimatorUpdateListener {
    override fun onAnimationUpdate(animation: ValueAnimator) {
        c.animationState = animation.animatedValue as Float
        c.invalidate();
    }
}
