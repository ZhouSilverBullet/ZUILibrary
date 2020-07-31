package com.uilib.book.chapter1.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @author  zhousaito
 * @date  2020/7/28 17:29
 * @version 1.0
 * @Dec 测试Path 中的 FillTypeView模式
 */
class PathFillTypeView @JvmOverloads
constructor(context: Context, attributeSet: AttributeSet? = null, defAttr: Int = 0) :
    View(context, attributeSet, defAttr) {
    val paint = Paint()
    val path = Path()
    val rectF = RectF()

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return
        paint.setColor(Color.BLUE)
        paint.style = Paint.Style.FILL

        canvas.save()

        rectF.set(100f, 100f, 350f, 350f)
        canvas.clipRect(rectF)
        // 200 x 200的正方形
        rectF.set(100f, 100f, 300f, 300f)
        path.addRect(rectF, Path.Direction.CCW)
//        canvas.drawPath(path, paint)

        rectF.set(100f, 100f, 300f, 300f)
        path.addCircle(300f, 300f, 50f, Path.Direction.CCW)
        path.fillType = Path.FillType.INVERSE_WINDING
        canvas.drawPath(path, paint)

        canvas.restore()
    }
}