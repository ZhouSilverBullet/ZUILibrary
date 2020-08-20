package com.uilib.anr

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AnrBroadcastReceiver : BroadcastReceiver() {

    companion object {
        const val TAG = "AnrBroadcastReceiver"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e(TAG, "onReceive")
        intent ?: return
        context ?: return

        start()
    }

    @Synchronized
    private fun start() {
        Log.e(TAG, "开始了")
        //睡眠10s钟
        Thread.sleep(10000)
        Log.e(TAG, "结束了")
    }

}