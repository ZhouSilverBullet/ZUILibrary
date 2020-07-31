package com.uilib.book.chapter1.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @author  zhousaito
 * @date  2020/7/28 10:05
 * @version 1.0
 * @Dec 略
 */
class PaintTestView
@JvmOverloads
constructor(context: Context, attributeSet: AttributeSet? = null, defAttr: Int = 0) :
    View(context, attributeSet, defAttr) {

    val paint = Paint().apply {
        style = Paint.Style.FILL
        strokeWidth = 50f
    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return
        paint.setColor(Color.RED)
        canvas.drawCircle(290f, 200f, 200f, paint)
        paint.setColor(0x7F00FF00)
        canvas.drawCircle(290f, 200f, 150f, paint)
        //1.画背景
//        canvas.drawRGB(23423423, 0, 0)

        //2. 画直线
        canvas.drawLine(100f, 500f, 400f, 500f, paint)

        //3. 画点
        paint.setColor(Color.BLUE)
        paint.style = Paint.Style.STROKE
        canvas.drawPoint(100f, 600f, paint)

        //4.Rect和RectF 构建矩形
//        val rect = Rect(10, 10, 200, 200)
//        val rect2 = Rect()
//        rect2.set(10, 10, 200, 200)
        //5. 绘制矩形
//        canvas.drawRect(rect, paint)
//        canvas.drawRect(rect2, paint)

//        Color.red()


    }
}