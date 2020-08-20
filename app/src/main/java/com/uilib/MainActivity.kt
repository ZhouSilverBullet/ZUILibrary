package com.uilib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.os.TraceCompat
import androidx.core.view.LayoutInflaterCompat
import com.uilib.anr.AnrActivity
import com.uilib.book.SelfViewActivity
import com.uilib.flowlayout.FlowLayoutActivity
import com.uilib.imageoption.ImageOptionActivity
import com.uilib.tanxian.TanXianActivity
import com.uilib.textdraw.TextDrawActivity
import com.uilib.touchevent.TouchDelegateActivity
import com.uilib.touchevent.TouchEvent2Activity
import com.uilib.touchevent.TouchEvent3Activity
import com.uilib.touchevent.TouchEventActivity
import com.uilib.yyb.YybButtonActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initEvent()
    }

    fun initEvent() {
        btnYyb.setOnClickListener {
            startActivity(Intent(this, YybButtonActivity::class.java))
        }

        btnFlowLayout.setOnClickListener {
            startActivity(Intent(this, FlowLayoutActivity::class.java))
        }

        btnTextDraw.setOnClickListener {
            startActivity(Intent(this, TextDrawActivity::class.java))
        }

        btnImageOption.setOnClickListener {
            startActivity(Intent(this, ImageOptionActivity::class.java))
        }

        btnSelfViewBook.setOnClickListener {
            startActivity(Intent(this, SelfViewActivity::class.java))
        }

        btnDeveloper.setOnClickListener {
            startActivity(Intent(this, TanXianActivity::class.java))
        }

        btnTouchEvent.setOnClickListener {
            startActivity(Intent(this, TouchEvent2Activity::class.java))

        }

        btnTouchEvent3.setOnClickListener {
            startActivity(Intent(this, TouchEvent3Activity::class.java))

        }

        btnTouchDelegate.setOnClickListener {
            startActivity(Intent(this, TouchDelegateActivity::class.java))
        }

        btnAnr.setOnClickListener {
            startActivity(Intent(this, AnrActivity::class.java))

        }
    }
}