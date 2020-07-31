package com.uilib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.TraceCompat
import com.uilib.book.SelfViewActivity
import com.uilib.flowlayout.FlowLayoutActivity
import com.uilib.imageoption.ImageOptionActivity
import com.uilib.textdraw.TextDrawActivity
import com.uilib.yyb.YybButtonActivity
import kotlinx.android.synthetic.main.activity_main.*

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
    }
}