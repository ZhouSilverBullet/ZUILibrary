package com.uilib.imageoption

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uilib.R
import kotlinx.android.synthetic.main.activity_image_option.*

/**
 * 图片裁剪
 */
class ImageOptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_option)

        btnNotClip.setOnClickListener {
            ivComplete.setImageDrawable(ivSource.drawable)
        }

        /**
         * 直接使用，不过出现长宽比和原来
         * 不一样，容易出现变形
         */
        btnClip.setOnClickListener {
            val output = Bitmap.createBitmap(
                ivSource.drawable.intrinsicWidth,
                ivSource.drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(output)
            ivSource.drawable.setBounds(
                0,
                0,
                ivSource.drawable.intrinsicWidth,
                ivSource.drawable.intrinsicHeight
            )
            ivSource.drawable.draw(canvas)

            val scaledBitmap = Bitmap.createScaledBitmap(
                output,
                ivComplete.measuredWidth,
                ivComplete.measuredHeight,
                true
            )
            ivComplete.setImageBitmap(scaledBitmap)
        }

        btnClip2.setOnClickListener {
            val output = Bitmap.createBitmap(
                ivSource.drawable.intrinsicWidth,
                ivSource.drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(output)
            ivSource.drawable.setBounds(
                0,
                0,
                ivSource.drawable.intrinsicWidth,
                ivSource.drawable.intrinsicHeight
            )
            ivSource.drawable.draw(canvas)

            val scaledBitmap = getScaledBitmap(
                output,
                ivComplete.measuredWidth,
                ivComplete.measuredHeight
            )

            ivComplete.setImageBitmap(scaledBitmap)
        }

        btnClip3.setOnClickListener {
            val output = Bitmap.createBitmap(
                ivSource.drawable.intrinsicWidth,
                ivSource.drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(output)
            ivSource.drawable.setBounds(
                0,
                0,
                ivSource.drawable.intrinsicWidth,
                ivSource.drawable.intrinsicHeight
            )
            ivSource.drawable.draw(canvas)

            val scaledBitmap = getScaledBitmapForMatrix(
                output,
                ivComplete.measuredWidth,
                ivComplete.measuredHeight
            )

            ivComplete.setImageBitmap(scaledBitmap)
        }

        btnClip4.setOnClickListener {
            val wRatio = ivComplete.width.div(ivSource.drawable.intrinsicWidth * 1.0f)
            val hRatio = ivComplete.height.div(ivSource.drawable.intrinsicHeight * 1.0f)

            val ratio = if (wRatio < hRatio) wRatio else hRatio

            val output = Bitmap.createBitmap(
                (ratio * ivSource.drawable.intrinsicWidth).toInt(),
                (ratio * ivSource.drawable.intrinsicHeight).toInt(),
                Bitmap.Config.ARGB_8888
            )

            val c = Canvas(output)
            ivSource.drawable.setBounds(
                0,
                0,
                (ratio * ivSource.drawable.intrinsicWidth).toInt(),
                (ratio * ivSource.drawable.intrinsicHeight).toInt()
            )
            ivSource.drawable.draw(c)

            //错切一下
            val matrix = Matrix()
//            matrix.setSkew(-0.3f, -0.3f)
            matrix.setRotate(30f)
            val copyBitmap =
                Bitmap.createBitmap(output, 0, 0, output.width, output.height, matrix, true)

            ivComplete.setImageBitmap(copyBitmap)
        }
    }

    /**
     * 计算等比缩放
     *
     * 宽度比例 = 目标宽度 / (图片宽度*.0.1f)
     * 高度比例 = 目标高度 / (图片高度*.0.1f)
     *
     * 选其中最小的值
     * 最终通过 原图宽高 * 最小值的比例，就可以了
     *
     * width = ratio * 图片宽度
     * height = ratio * 图片高度
     *
     */
    private fun getScaledBitmap(bitmap: Bitmap, measuredWidth: Int, measuredHeight: Int): Bitmap {
        val bWidth = bitmap.width
        val bHeight = bitmap.height
        val wRatio = measuredWidth.div(bWidth * 1.0f)
        val hRatio = measuredHeight.div(bHeight * 1.0f)
        //返回一个小比例
        val ratio = if (wRatio < hRatio) wRatio else hRatio

        return Bitmap.createScaledBitmap(
            bitmap,
            (bWidth * ratio).toInt(),
            (bHeight * ratio).toInt(),
            true
        )
    }

    private fun getScaledBitmapForMatrix(
        bitmap: Bitmap,
        measuredWidth: Int,
        measuredHeight: Int
    ): Bitmap {
        val bWidth = bitmap.width
        val bHeight = bitmap.height
        val wRatio = measuredWidth.div(bWidth * 1.0f)
        val hRatio = measuredHeight.div(bHeight * 1.0f)
        //返回一个小比例
        val ratio = if (wRatio < hRatio) wRatio else hRatio
        val matrix = Matrix()
        matrix.setScale(ratio, ratio)
        return Bitmap.createBitmap(bitmap, 0, 0, bWidth, bHeight, matrix, true)
    }


}