package com.uilib.book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.TouchDelegate
import com.uilib.R
import com.uilib.book.chapter1.Chapter1Activity
import com.uilib.book.chapter13.GestureDetector1Activity
import com.uilib.book.chapter2.AnimationActivity
import com.uilib.book.chapter3.AnimatorActivity
import kotlinx.android.synthetic.main.activity_self_view.*
import java.lang.StringBuilder
import java.util.concurrent.atomic.AtomicReference

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
        btnChapter3.setOnClickListener {
            startActivity(Intent(this, AnimatorActivity::class.java))
        }
        btnChapter13.setOnClickListener {
            startActivity(Intent(this, GestureDetector1Activity::class.java))
        }
//        AtomicReference<Int>()
        val sb = StringBuilder();


    }
}