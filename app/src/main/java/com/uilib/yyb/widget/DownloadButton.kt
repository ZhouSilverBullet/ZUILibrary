package com.uilib.yyb.widget

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.uilib.R
import kotlin.concurrent.thread

/**
 * @author  zhousaito
 * @date  2020/7/23 11:29
 * @version 1.0
 * @Dec 略
 */
class DownloadButton
@JvmOverloads
constructor(context: Context, set: AttributeSet? = null, defAttr: Int = 0) :
    View(context, set, defAttr) {

    companion object {
        const val TAG = "DownloadButton"
    }

    val bitmap: Bitmap by lazy {
        BitmapFactory.decodeResource(context.resources, R.drawable.flicker)
    }

//    var bitmap: Bitmap? = null

    var bitmapLeft = 0f

    init {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)

    }

    val mPaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 2f
        isAntiAlias = true

        color = ContextCompat.getColor(getContext(), R.color.colorAccent)
    }

    val mBoundsRect = Rect()

    var mProgress = 0.55f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        var width = 0
        var height = 0

        when (widthMode) {

            MeasureSpec.EXACTLY -> {
                width = widthSize
            }
            MeasureSpec.UNSPECIFIED,
            MeasureSpec.AT_MOST -> {
                //暂时先给个固定值
                width = 300
            }
        }

        when (heightMode) {

            MeasureSpec.EXACTLY -> {
                height = heightSize
            }
            MeasureSpec.UNSPECIFIED,
            MeasureSpec.AT_MOST -> {
                //暂时先给个固定值
                height = 100
            }
        }

        // 把测量的值保存起来
        // 这个方法之后，就得到了测量的结果了
        // mMeasuredWidth = measuredWidth;
        // mMeasuredHeight = measuredHeight;
        // getMeasuredHeight 和 getMeasuredWidth就有值了
        setMeasuredDimension(width, height)

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
//        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
//        val canvas = Canvas(bitmap!!)
//        bitmap2.copy(Bitmap.Config.ARGB_8888, true)

        bitmapLeft = -bitmap.width.toFloat()

        thread(start = true) {
            while (true) {
                if (bitmapLeft <= measuredWidth.toFloat() * mProgress) {
                    bitmapLeft += 30f
                } else {
                    bitmapLeft = -bitmap.width.toFloat()
                }
                Log.e(TAG, "bitmapLeft : ${bitmapLeft}")
                Thread.sleep(20)
                postInvalidate()
            }
        }
    }


    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

//        canvas.drawColor(Color.RED)

//        drawBg(canvas);

        //画边框
        drawBolder(canvas)

        //画进度
        drawProgress(canvas)

        //画文字
        drawText(canvas)

        //文字变色处理
        drawColorText(canvas)
    }

    private fun drawBg(canvas: Canvas) {
//        mPaint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP))
//        canvas.drawBitmap(bitmap!!, bitmapLeft, 0f, mPaint)
//        mPaint.setXfermode(null)
    }

    private fun drawColorText(canvas: Canvas) {
        val textValue = "加载中$mProgress%"

        mPaint.color = ContextCompat.getColor(context, R.color.white)
        mPaint.style = Paint.Style.FILL
        mPaint.textSize = 40f
        mPaint.strokeWidth = 2f

        canvas.save()

        canvas.clipRect(0, 0, (measuredWidth * mProgress).toInt(), measuredHeight)
        canvas.drawText(
            textValue, (measuredWidth / 2f - (mBoundsRect.right - mBoundsRect.left) / 2.0f),
            (measuredHeight / 2.0f + (mBoundsRect.bottom - mBoundsRect.top) / 2.0f), mPaint
        )

        canvas.restore()
    }

    private fun drawText(canvas: Canvas) {
        val textValue = "加载中$mProgress%"
        mPaint.color = ContextCompat.getColor(context, R.color.colorPrimaryDark)
        mPaint.style = Paint.Style.FILL
        mPaint.textSize = 40f
        mPaint.strokeWidth = 2f

        mPaint.getTextBounds(textValue, 0, textValue.length, mBoundsRect)
        canvas.drawText(
            textValue, (measuredWidth / 2f - (mBoundsRect.right - mBoundsRect.left) / 2.0f),
            (measuredHeight / 2.0f + (mBoundsRect.bottom - mBoundsRect.top) / 2.0f), mPaint
        )

    }

    private fun drawProgress(canvas: Canvas) {
        mPaint.style = Paint.Style.FILL
        mPaint.color = ContextCompat.getColor(context, R.color.colorPrimaryDark)

        val output = Bitmap.createBitmap(
            ((measuredWidth - 10f) * mProgress).toInt(),
            measuredHeight - 10,
            Bitmap.Config.ARGB_8888
        )
        val c = Canvas(output)
        val paint = Paint()
        paint.style = Paint.Style.FILL
        paint.color = ContextCompat.getColor(context, R.color.colorPrimaryDark)
        paint.isAntiAlias = true
        val rect = Rect(0, 0, measuredWidth - 10, measuredHeight - 10)
        c.drawRect(rect, paint)

        //进度条画上去
        canvas.drawBitmap(output, 5f, 5f, mPaint)
        mPaint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP))
        canvas.drawBitmap(bitmap, bitmapLeft, 5f, mPaint)
        mPaint.setXfermode(null)
//        val rect = Rect(0, 0, (measuredWidth * mProgress).toInt(), measuredHeight)
//        canvas.drawRect(rect, mPaint)
    }

    private fun drawBolder(canvas: Canvas) {
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 5f
        mPaint.color = ContextCompat.getColor(context, R.color.colorPrimary)
        val rect = Rect(0, 0, measuredWidth, measuredHeight)
//        Log.e(TAG, " $width   $height")
//        Log.e(TAG, " $measuredWidth   $measuredHeight")

        canvas.drawRect(rect, mPaint)
    }

    //进度条设置
    fun setProgress(progress: Float) {
        mProgress = progress

        invalidate()
    }

}