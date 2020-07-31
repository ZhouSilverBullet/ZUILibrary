package com.uilib.textdraw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uilib.R
import kotlinx.android.synthetic.main.activity_text_draw.*

class TextDrawActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_draw)

        myTextView.setOnClickListener {
            myTextView.setText("123")
        }

        myTextView2.setOnClickListener {
            myTextView2.setText("123")
            myTextView2.setText("123434")
            myTextView2.setText("123234234")
        }
    }
}