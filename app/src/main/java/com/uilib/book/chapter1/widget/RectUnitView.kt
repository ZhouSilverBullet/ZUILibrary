package com.uilib.book.chapter1.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

/**
 * @author  zhousaito
 * @date  2020/7/28 18:36
 * @version 1.0
 * @Dec 合并两个矩阵
 */
class RectUnitView @JvmOverloads
constructor(context: Context, attributeSet: AttributeSet? = null, defAttr: Int = 0) :
    View(context, attributeSet, defAttr) {
    val rect = Rect()
    val rect1 = Rect()
    val rect2 = Rect()
    val paint = Paint()

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        rect1.set(100, 100, 120, 120)
        rect2.set(210, 210, 240, 240)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f
        paint.color = Color.RED
        canvas.drawRect(rect1, paint)
        canvas.drawRect(rect2, paint)

        rect1.union(rect2)
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.strokeWidth = 40f
        paint.color = Color.argb(0x7f, 0xff, 0, 0)
        canvas.drawRect(rect1, paint)

        //paint stroke
//        paint.style = Paint.Style.STROKE
//        paint.color = Color.argb(0x7f, 0xff, 0, 0)
//        paint.strokeWidth = 100f
//
//        rect.set(400, 400, 600, 600)
//        canvas.drawRect(rect, paint)
//
//        //paint fill
//        paint.style = Paint.Style.FILL
//        paint.color = Color.argb(0x7f, 0, 0xff, 0)
//
//        rect.set(400, 400, 600, 600)
//        canvas.drawRect(rect, paint)
//
        //paint fill
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.color = Color.argb(0x7f, 0, 0, 0xff)
        paint.strokeWidth = 100f

        rect.set(400, 400, 600, 600)
        canvas.drawRect(rect, paint)

    }
}