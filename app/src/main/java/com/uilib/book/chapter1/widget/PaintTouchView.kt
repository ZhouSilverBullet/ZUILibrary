package com.uilib.book.chapter1.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

/**
 * @author  zhousaito
 * @date  2020/7/28 13:51
 * @version 1.0
 * @Dec 略
 */
class PaintTouchView
@JvmOverloads
constructor(context: Context, set: AttributeSet? = null, defAttr: Int = 0) :
    View(context, set, defAttr) {
    companion object {
        const val TAG = "PaintTouchView"
    }

    val paint = Paint()

    val rect = Rect().apply {
        set(10, 10, 200, 200)
    }

    var mTouchX = -1f
    var mTouchY = -1f

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return super.onTouchEvent(event)

        Log.e(TAG, " onTouchEvent " + event.action)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mTouchX = event.x
                mTouchY = event.y

                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                mTouchX = -1f
                mTouchY = -1f
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return
        if (rect.contains(mTouchX.toInt(), mTouchY.toInt())) {
            paint.setColor(Color.GREEN)
        } else {
            paint.setColor(Color.RED)
        }
        canvas.drawRect(rect, paint)
    }
}