package com.uilib.tanxian

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uilib.R
import com.uilib.tanxian.chapter8.CreateWindowActivity
import kotlinx.android.synthetic.main.activity_tan_xian.*

/**
 * android 开发艺术探索
 */
class TanXianActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tan_xian)

        btnCreateWindow.setOnClickListener {
            startActivity(Intent(this, CreateWindowActivity::class.java))
        }
    }
}