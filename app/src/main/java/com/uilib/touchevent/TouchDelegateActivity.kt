package com.uilib.touchevent

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.TouchDelegate
import android.view.View
import android.widget.Toast
import com.uilib.R
import kotlinx.android.synthetic.main.activity_touch_delegate.*

/**
 * 点击范围变大一个按钮
 */
class TouchDelegateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch_delegate)

        btnClick.post {
            val rect = Rect()
            if (View::class.java.isInstance(btnClick.parent)) {
                val parent = btnClick.parent as View
                parent.getHitRect(rect)
                rect.bottom = rect.bottom - 400
                val touchDelegate = TouchDelegate(rect, btnClick)
                parent.touchDelegate = touchDelegate
            }
        }
        btnClick.setOnClickListener {
            Toast.makeText(this, "你好啊", Toast.LENGTH_LONG).show()
        }
    }
}