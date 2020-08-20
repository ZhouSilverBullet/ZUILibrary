package com.uilib.book.chapter13.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView

class MyPhotoView
@JvmOverloads
constructor(context: Context, set: AttributeSet? = null, defAttr: Int = 0) :
    AppCompatImageView(context, set, defAttr), GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener {
    companion object {
        const val TAG = "MyPhotoView"
    }

    val imgMatrix = Matrix()

    var isExecScale = false

    val gestureDetector = GestureDetector(context, this).apply {
        setOnDoubleTapListener(this@MyPhotoView)
    }

    override fun onShowPress(e: MotionEvent?) {

    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return false
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return false
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        animate().translationY(-distanceY).setDuration(50).start()
        animate().translationX(-distanceX).setDuration(50).start()

        return false
    }

    override fun onLongPress(e: MotionEvent?) {

    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        return false
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        e ?: return false
        if (e.action == MotionEvent.ACTION_UP) {
            if (isExecScale) {

                animate().scaleX(1f)
                    .scaleY(1f)
                    .setDuration(300)
                    .start()
                isExecScale = false
            } else {
                val defaultPivotX = pivotX
                val defaultPivotY = pivotY

                pivotX = e.getX()
                pivotY = e.getY()

                animate().scaleX(3f)
                    .scaleY(3f)
                    .setDuration(300)
                    .start()

//                scaleX = 3f
//                scaleY = 3f
                isExecScale = true

//                pivotX = defaultPivotX
//                pivotY = defaultPivotY
            }
        }
        Log.e(TAG, "onDoubleTapEvent ${e.action}")
        return false
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        return false
    }

    override fun setImageResource(resId: Int) {
        super.setImageResource(resId)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        drawable.setBounds(0, 0, width, height)
        drawable.draw(Canvas(bitmap))

    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }
}