package com.uilib.tanxian.chapter8

import android.content.Context
import android.graphics.PixelFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.ImageView
import com.uilib.R

class CreateWindowActivity : AppCompatActivity() {
    val lp = WindowManager.LayoutParams(
        WindowManager.LayoutParams.WRAP_CONTENT,
        WindowManager.LayoutParams.WRAP_CONTENT, 0, 0, PixelFormat.TRANSPARENT
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_window)


        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        lp.x = 0
        lp.y = 0
        val imageView = ImageView(this).apply {
            setImageResource(R.mipmap.ic_launcher)
        }
        lp.type = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
        wm.addView(imageView, lp)

        imageView.setOnTouchListener { v, event ->
            val moveX = event.rawX.toInt()
            val moveY = event.rawX.toInt()
            when (event.action) {
                MotionEvent.ACTION_MOVE -> {
//                    imageView.layout(
//                        moveX,
//                        moveY,
//                        moveX + imageView.width,
//                        moveY + imageView.height
//                    )
                    val lp2 = WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT, 0, 0, PixelFormat.TRANSPARENT)
                    lp2.type = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    lp2.x = moveX
                    lp2.y = moveY
                    wm.updateViewLayout(imageView, lp2)
                }
                else -> {
                }
            }
            return@setOnTouchListener false
        }
    }
}