package com.uilib.book.chapter1.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @author  zhousaito
 * @date  2020/7/28 10:05
 * @version 1.0
 * @Dec  1.2 路径
 */
class PaintTest2View
@JvmOverloads
constructor(context: Context, attributeSet: AttributeSet? = null, defAttr: Int = 0) :
    View(context, attributeSet, defAttr) {

    val paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 10f
        color = Color.RED
    }

    val rectF = RectF()

    val path = Path().apply {
//        moveTo()
//        lineTo()
//        close()
        rectF.set(10f, 10f, 200f, 200f)
        arcTo(rectF, 0f, 180f, false)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        canvas.drawPath(path, paint)
        paint.color = Color.argb(0x7f, 0x00, 0xff, 0x00)
        canvas.drawRect(rectF, paint)

        //2. 圆角矩阵
        rectF.set(210f, 210f, 400f, 400f)
        canvas.drawRoundRect(rectF, 100f, 50f, paint)


        path.reset()

//        path.moveTo(100f, 400f)
//        path.lineTo(100f, 600f)
//        path.lineTo(400f, 600f)
//        path.close()

        rectF.set(100f, 100f, 600f, 400f)
        path.addRect(rectF, Path.Direction.CW)
        //画path，三角形
        canvas.drawPath(path, paint)
        val str = "adfasfasd";
        paint.textSize = 30f
        paint.strokeWidth = 4f
        canvas.drawTextOnPath(str, path, 0f, 0f, paint)

        //path画矩形
        val radii = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 50f, 50f)
        rectF.set(10f, 800f, 500f, 600f)
        path.addRoundRect(rectF, radii, Path.Direction.CCW)
        paint.style = Paint.Style.FILL
        canvas.drawPath(path, paint)

        path.rewind()
        //path画圆
        path.addCircle(300f, 1000f, 200f, Path.Direction.CCW)
        canvas.drawPath(path, paint)

        paint.textSize = 30f
        paint.strokeWidth = 4f
        canvas.drawTextOnPath(str, path, 0f, 0f, paint)

        //path 画圆弧
        //todo Path#arcTo 这个也是圆弧，有什么区别
        // 这个好像是 走出来的圆弧，pathTo类似
        rectF.set(200f, 1300f, 400f, 1500f)
        path.addArc(rectF, 0f, 160f)
        canvas.drawPath(path, paint)

        //path addOval 画椭圆
        rectF.set(200f, 1500f, 400f, 1600f)
        path.addOval(rectF, Path.Direction.CCW)
        canvas.drawPath(path, paint)

        //填充模式

    }
}