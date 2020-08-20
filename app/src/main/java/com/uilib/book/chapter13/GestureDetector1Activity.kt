package com.uilib.book.chapter13

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.GestureDetector
import androidx.appcompat.app.AppCompatActivity
import com.uilib.R
import kotlinx.android.synthetic.main.activity_gesture_detector1.*

class GestureDetector1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gesture_detector1)
//        photoView.setZoomable(true)
//        photoView.setBackgroundColor(resources.getColor(R.color.colorAccent))
//        photoView.setImageDrawable(
//            BitmapDrawable(
//                resources,
//                BitmapFactory.decodeResource(
//                    resources,
//                    R.drawable.mobi_icon
//                )
//            )
//        )
//        photoView.setOnMatrixChangeListener {
//
//        }
//
//        photoView.setOnPhotoTapListener { view, x, y ->
//
//        }
        myPhotoView.setImageResource(R.drawable.mobi_icon)
    }
}