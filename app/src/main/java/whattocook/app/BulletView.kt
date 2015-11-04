package whattocook.app

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

import java.util.Random

class BulletView(c: Context, a: AttributeSet) : View(c, a) {
    private val rc: Int

    init {
        val r = rand.nextInt(256)
        val g = rand.nextInt(256)
        val b = rand.nextInt(256)
        rc = Color.rgb(r, g, b)
    }

    override fun onMeasure(w: Int, h: Int) {
        setMeasuredDimension(w, h)
    }

    override fun onDraw(c: Canvas) {
        super.onDraw(c)

        val w = width
        val h = height

        val p = Paint()
        p.flags = Paint.ANTI_ALIAS_FLAG
        p.color = rc
        p.style = Paint.Style.FILL
        c.drawCircle((w / 2).toFloat(), (h / 2).toFloat(), (w / 2).toFloat(), p)
    }

    companion object {

        private val rand = Random()
    }

}
