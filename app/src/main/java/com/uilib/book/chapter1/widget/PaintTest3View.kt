package com.uilib.book.chapter1.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View

/**
 * @author  zhousaito
 * @date  2020/7/30 09:58
 * @version 1.0
 * @Dec 略
 */

class PaintTest3View
@JvmOverloads
constructor(context: Context, attributeSet: AttributeSet? = null, defAttr: Int = 0) :
    View(context, attributeSet, defAttr) {

    val paint = Paint()
    val path = Path()

    val typeface: Typeface = Typeface.createFromAsset(context.assets, "迷你简萝卜.ttf")

    val floatArr = floatArrayOf(80f, 100f, 80f, 180f, 80f, 260f)


    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        paint.isAntiAlias = true
        paint.textAlign = Paint.Align.LEFT
        paint.setARGB(0xff, 0xff, 0, 0)
        paint.strokeWidth = 10f
        paint.textSize = 60f
//        //粗体
//        paint.isFakeBoldText = true
//        //下划线
//        paint.isUnderlineText = true
//        //斜体
//        paint.textSkewX = -0.3f
//        //删除线
//        paint.isStrikeThruText = true
//        paint.textScaleX = 2f

//        canvas.drawText("找到 one peace 成为海贼王", 100f, 100f, paint)

        // 1.3.2 Canvas 绘制文本
//        canvas.drawText()
//        canvas.drawPosText("找到one peace成为海贼王", floatArr, paint)
//        canvas.drawPosText("海贼王", floatArr, paint)
        //path上绘制文字
//        path.addCircle(300f, 300f, 200f, Path.Direction.CW)
//        canvas.drawTextOnPath("找到one peace成为海贼王", path, -40f, 0f, paint)

        //1.3.3设置字体样式
//        paint.typeface = Typeface.SERIF
//        val faceStyle = Typeface.defaultFromStyle(Typeface.BOLD_ITALIC)
//        paint.typeface = faceStyle
//        Typeface.create()
        paint.typeface = typeface
        canvas.drawText("找到 one peace 成为海贼王", 100f, 100f, paint)
    }
}