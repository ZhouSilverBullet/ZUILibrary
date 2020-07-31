package com.uilib.book.chapter1.widget2

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.uilib.R

/**
 * @author  zhousaito
 * @date  2020/7/30 14:06
 * @version 1.0
 * @Dec 略
 */
class CanvasView @JvmOverloads
constructor(context: Context, attributeSet: AttributeSet? = null, defAttr: Int = 0) :
    View(context, attributeSet, defAttr) {
    val paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }

    val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.mobi_icon)

    var rect = Rect()
    val path = Path()

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        //1.平移
//        paint.setARGB(0x7f, 0, 0xFF, 0)
//        rect.set(100, 100, 500, 500)
//        canvas.drawRect(rect, paint)
//        canvas.translate(100f, 100f)
//        paint.setARGB(0x7f, 0xFF, 0, 0)
//        canvas.drawRect(rect, paint)

        //clip 裁剪
        path.addCircle(300f,300f, 200f, Path.Direction.CCW)
        canvas.clipPath(path)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
    }
}