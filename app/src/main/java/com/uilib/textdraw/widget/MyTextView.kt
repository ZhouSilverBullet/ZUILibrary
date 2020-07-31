package com.uilib.textdraw.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

/**
 * @author  zhousaito
 * @date  2020/7/24 09:56
 * @version 1.0
 * @Dec 略
 */
class MyTextView
@JvmOverloads
constructor(context: Context, attributeSet: AttributeSet? = null, defAttr: Int = 0) :
    AppCompatTextView(context, attributeSet, defAttr) {
    companion object {
        const val TAG = "MyTextView"
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.e(TAG, "MyTextView onDraw ")
    }
}