package com.uilib.book.chapter3

import android.animation.Animator
import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.uilib.R
import kotlinx.android.synthetic.main.activity_animator.*

class AnimatorActivity : AppCompatActivity() {
    companion object {
        const val TAG = "AnimatorActivity"
        val IMG_LIST = intArrayOf(R.drawable.pic_1, R.drawable.pic_2, R.drawable.pic_3)
    }

    var i = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator)

        btnFirstUse.setOnClickListener {
            val left = tvMove.left
            val top = tvMove.top
            val right = tvMove.right
            val bottom = tvMove.bottom
            val animator = ValueAnimator.ofInt(0, 400)
            animator.addUpdateListener {
                Log.e(TAG, "UpdateListener : " + it.animatedValue)
                val i = it.animatedValue as Int
//                tvMove.layout(left + i, top + i, right + i, bottom + i);
                tvMove.layout(i, i, tvMove.width + i, tvMove.height + i);
            }
            animator.repeatCount = ValueAnimator.INFINITE
            animator.duration = 3000
            animator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                    Log.e(TAG, "onAnimationRepeat")
                }

                override fun onAnimationEnd(animation: Animator?) {
                    Log.e(TAG, "onAnimationEnd")
                }

                override fun onAnimationCancel(animation: Animator?) {
                    Log.e(TAG, "onAnimationCancel")
                }

                override fun onAnimationStart(animation: Animator?) {
                    Log.e(TAG, "onAnimationStart")
                }

            })
            animator.start()
        }

        tvMove.setOnClickListener {
            Toast.makeText(this, "aaaa", Toast.LENGTH_LONG).show()
        }

        ivLoading.post {
            val height = ivLoading.height
            val left = ivLoading.left
            val right = ivLoading.right
            val top = ivLoading.top
            val bottom = ivLoading.bottom

            val animator = ValueAnimator.ofInt(0, -300, 0)
            animator.duration = 2000
            animator.addUpdateListener {
                val animatedValue = it.animatedValue as Int
                ivLoading.layout(left, top + animatedValue, right, bottom + animatedValue)
            }
            animator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                    i++
                    ivLoading.setImageResource(IMG_LIST[i % IMG_LIST.size])
                }

                override fun onAnimationEnd(animation: Animator?) {
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
//                    ivLoading.setImageResource(IMG_LIST[i % IMG_LIST.size])
                }

            })
            animator.setEvaluator(object : TypeEvaluator<Int> {
                override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
                    val start = startValue
                    return start + ((endValue - startValue) * fraction).toInt()
                }
            })
            animator.repeatCount = ValueAnimator.INFINITE
            animator.start()
        }


    }
}