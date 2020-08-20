package com.uilib.book.chapter2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.*
import com.uilib.R
import kotlinx.android.synthetic.main.activity_animation.*

class AnimationActivity : AppCompatActivity() {
    companion object {
        const val TAG = "AnimationActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        btnAlpha.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.alpha_1)
            ivIcon.startAnimation(animation)

            scaleAnim()
        }

        btnScale.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.scale_2)
            ivIcon.startAnimation(animation)
        }

        btnTranslate.setOnClickListener {
//            val animation = AnimationUtils.loadAnimation(this, R.anim.translate_1)
//            ivIcon.startAnimation(animation)
            translate()
        }
        btnRotation.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.rotation_1)
            ivIcon.startAnimation(animation)
        }

        btnSet.setOnClickListener {
            val set = AnimationSet(false)
            set.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {
                    Log.e(TAG, "onAnimationRepeat");
                }

                override fun onAnimationEnd(animation: Animation?) {
                    Log.e(TAG, "onAnimationEnd");
                }

                override fun onAnimationStart(animation: Animation?) {
                    Log.e(TAG, "onAnimationStart");
                }
            })

            val rotateAnimation = RotateAnimation(
                30f,
                -30f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
            rotateAnimation.repeatCount = 1
            rotateAnimation.duration = 2000
            rotateAnimation.fillAfter = true

            val scaleAnimation = ScaleAnimation(
                0.8f, 1.8f, 0.8f, 1.8f, Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
            scaleAnimation.repeatCount = 2
            scaleAnimation.duration = 800
            scaleAnimation.fillAfter = true
            scaleAnimation.interpolator = OvershootInterpolator()


            set.addAnimation(rotateAnimation)
            set.addAnimation(scaleAnimation)
            set.fillAfter = true
            set.duration = 2000
            //这个设置了没什么作用
            set.repeatCount = Animation.INFINITE
            set.repeatMode = Animation.REVERSE

            ivIcon.startAnimation(set)

//            ivIcon.postDelayed({}, 5000)
        }

        ivScan.setOnClickListener {
            setAnim(vScan, 600)
            setAnim(vScan1, 1200)
            setAnim(vScan2, 1800)
            setAnim(vScan3, 0)
//            ivScan.postDelayed({setAnim(vScan1)}, 1000)
//            ivScan.postDelayed({setAnim(vScan2)}, 2000)

        }
    }

    private fun setAnim(view: View, offset: Long) {
        val scaleAnimation = ScaleAnimation(
            1f,
            3f,
            1f,
            3f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        scaleAnimation.interpolator = AccelerateInterpolator()
        scaleAnimation.duration = 2000
        scaleAnimation.repeatCount = Animation.INFINITE

        val alphaAnimation = AlphaAnimation(0.5f, 0f)
        alphaAnimation.duration = 2000
        alphaAnimation.repeatCount = Animation.INFINITE

        val set = AnimationSet(false)
        set.addAnimation(scaleAnimation)
        set.addAnimation(alphaAnimation)
//            set.repeatCount = Animation.INFINITE
        set.repeatMode = Animation.RESTART
        set.startOffset = offset
        view.startAnimation(set)
    }

    private fun scaleAnim() {
//        ScaleAnimation()

        val scaleAnimation = ScaleAnimation(
            1.0f,
            1.2f,
            1.0f,
            1.2f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        scaleAnimation.duration = 6000
        scaleAnimation.interpolator = BounceInterpolator()
        scaleAnimation.fillAfter = true

        ivIcon2.startAnimation(scaleAnimation)
    }

    private fun translate() {
        val animation = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 2f,
            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 2f
        )
        animation.duration = 2000
        ivIcon.startAnimation(animation)
    }


}