package com.uilib.book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uilib.R
import com.uilib.book.chapter1.Chapter1Activity
import com.uilib.book.chapter2.AnimationActivity
import kotlinx.android.synthetic.main.activity_self_view.*

class SelfViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_self_view)
        initEvent()
    }

    private fun initEvent() {
        btnChapter1.setOnClickListener {
          startActivity(Intent(this, Chapter1Activity::class.java))
        }
        btnChapter2.setOnClickListener {
          startActivity(Intent(this, AnimationActivity::class.java))
        }
    }
}