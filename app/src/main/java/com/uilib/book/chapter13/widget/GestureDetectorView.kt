package com.uilib.book.chapter13.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import kotlin.math.abs

class GestureDetectorView
@JvmOverloads
constructor(context: Context, set: AttributeSet? = null, defAttr: Int = 0) :
    AppCompatTextView(context, set, defAttr), GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener {
    companion object {
        const val TAG = "GestureDetectorView"
    }

    val gestureDetector = GestureDetector(context, this)

    init {
        gestureDetector.setOnDoubleTapListener(this)
    }

    override fun onShowPress(e: MotionEvent?) {
        Log.e(TAG, "onShowPress")
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        Log.e(TAG, "onSingleTapUp")
        return false
    }

    override fun onDown(e: MotionEvent?): Boolean {
        Log.e(TAG, "onDown")
        return true
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        Log.e(TAG, "onFling")
        return false
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
//        Log.e(TAG, "onScroll")
        e1 ?: return false
        e2 ?: return false

        val curX = abs(abs(e1.getX()) - Math.abs(e2.getX()))
        val curY = abs(abs(e1.getY()) - abs(e2.getY()))
        if (curX > curY) {
            Log.e(TAG, "onScroll x 轴滑动")
        } else {
            Log.e(TAG, "onScroll Y 轴滑动")
        }
        return false
    }

    override fun onLongPress(e: MotionEvent?) {
        Log.e(TAG, "onLongPress")
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        Log.e(TAG, "onDoubleTap")
        return false;
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        Log.e(TAG, "onDoubleTapEvent")
        return false;
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        Log.e(TAG, "onSingleTapConfirmed")
        return false;
    }
}