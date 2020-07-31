package com.uilib.book.chapter1.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * @author  zhousaito
 * @date  2020/7/28 18:27
 * @version 1.0
 * @Dec  rect intersect 判断矩阵是否相交
 */
class RectIntersectsView @JvmOverloads
constructor(context: Context, attributeSet: AttributeSet? = null, defAttr: Int = 0) :
    View(context, attributeSet, defAttr) {

    companion object {
        const val TAG = "RectIntersectsView"
    }

    val rect = Rect()
    val rect2 = Rect()
    val paint = Paint()

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        paint.color = Color.RED
        rect.set(10, 10, 200, 200)
        canvas.drawRect(rect, paint)

        paint.color = Color.GREEN
        rect2.set(190, 190, 290, 290)
        canvas.drawRect(rect2, paint)

        Log.d(TAG, " intersect: ${rect.intersect(rect2)} ")
    }
}

