package com.uilib.yyb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.uilib.R
import kotlinx.android.synthetic.main.activity_yybbutton.*

class YybButtonActivity : AppCompatActivity(), Handler.Callback {

    val handler = Handler(this)
    var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yybbutton)

//        handler.sendEmptyMessage(100)
    }

    override fun handleMessage(msg: Message): Boolean {
        i += 10
        if (i > 100) {
            i = 10;
        }
        val progress = i / 100f
        Log.e("YybButtonActivity", " $progress")
        downloadButton.setProgress(progress)
        handler.sendEmptyMessageDelayed(100, 500)
        return true
    }
}