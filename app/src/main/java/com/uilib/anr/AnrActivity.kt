package com.uilib.anr

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.view.ViewStub
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.uilib.R
import kotlinx.android.synthetic.main.activity_anr.*

class AnrActivity : AppCompatActivity(), Handler.Callback {
    val localBroadcastManager by lazy {
        LocalBroadcastManager.getInstance(this)
    }
    val broadcastReceiver by lazy {
        AnrBroadcastReceiver()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anr)
        registerReceiver(
            broadcastReceiver,
            IntentFilter("com.anr.anr_action")
        )

        btnClick.setOnClickListener {
            Toast.makeText(this, "你好", Toast.LENGTH_LONG).show()
        }


        val intent = Intent()
        intent.setFlags(Intent.FLAG_RECEIVER_FOREGROUND)
        intent.setAction("com.anr.anr_action")
        sendBroadcast(intent)
//        Handler(this).sendEmptyMessageAtTime()
        ViewStub(this, 0).inflate()
        HandlerThread("adf").start()

    }

    override fun onDestroy() {
        unregisterReceiver(broadcastReceiver)
        super.onDestroy()
    }

    override fun handleMessage(msg: Message): Boolean {
        return true
    }
}