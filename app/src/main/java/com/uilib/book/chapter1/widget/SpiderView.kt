package com.uilib.book.chapter1.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

/**
 * @author  zhousaito
 * @date  2020/7/28 20:26
 * @version 1.0
 * @Dec 略
 */
class SpiderView @JvmOverloads
constructor(context: Context, set: AttributeSet? = null, defAttr: Int = 0) :
    View(context, set, defAttr) {

    var radius = 300f
    var width = 300f
    val path = Path()
    val paint = Paint()

    val pointX = 400f;
    val pointY = 400f;

    val points = ArrayList<List<SpiderPoint>>(6)
    val lines = ArrayList<SpiderPoint>();

    //进度值
    val progressPercents = ArrayList<Float>().apply {
        add(0.55f)
        add(0.23f)
        add(0.60f)
        add(0.9f)
        add(0.8f)
        add(0.7f)
    }

    val arc30 = Math.toRadians(30.0).toFloat()

    //记录点
    class SpiderPoint(val x: Float, val y: Float)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        //把6个点算出来
        if (points.isNotEmpty()) {
            points.clear()
        }

        if (lines.isNotEmpty()) {
            lines.clear()
        }


        for (i in 0 until 5) {
            val list = ArrayList<SpiderPoint>()
            val currentRadius = radius - i * 40
            list.add(SpiderPoint(pointX - currentRadius, pointY))
            list.add(
                SpiderPoint(
                    pointX - currentRadius * sin(arc30),
                    pointY + currentRadius * cos(arc30)
                )
            )
            list.add(
                SpiderPoint(
                    pointX + currentRadius * sin(arc30),
                    pointY + currentRadius * cos(arc30)
                )
            )
            list.add(SpiderPoint(pointX + currentRadius, pointY))
            list.add(
                SpiderPoint(
                    pointX + currentRadius * sin(arc30),
                    pointY - currentRadius * cos(arc30)
                )
            )
            list.add(
                SpiderPoint(
                    pointX - currentRadius * sin(arc30),
                    pointY - currentRadius * cos(arc30)
                )
            )
            points.add(list)

            if (i == 0) {
                lines.add(SpiderPoint(pointX - currentRadius, pointY))
                lines.add(
                    SpiderPoint(
                        pointX - currentRadius * sin(arc30),
                        pointY + currentRadius * cos(arc30)
                    )
                )
                lines.add(
                    SpiderPoint(
                        pointX + currentRadius * sin(arc30),
                        pointY + currentRadius * cos(arc30)
                    )
                )
                lines.add(SpiderPoint(pointX + currentRadius, pointY))
                lines.add(
                    SpiderPoint(
                        pointX + currentRadius * sin(arc30),
                        pointY - currentRadius * cos(arc30)
                    )
                )
                lines.add(
                    SpiderPoint(
                        pointX - currentRadius * sin(arc30),
                        pointY - currentRadius * cos(arc30)
                    )
                )
            }
        }

    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        for (i in 0 until points.size) {
            for (j in points[i].indices) {
                val point = points[i][j]
                if (j == 0) {
                    path.moveTo(point.x, point.y)
                } else {
                    path.lineTo(point.x, point.y)
                }
            }
            path.close()
        }
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 4f

        // 画线
        for (i in 0 until lines.size) {
            path.moveTo(lines[i].x, lines[i].y)
            path.lineTo(pointX, pointY)
        }

        canvas.drawPath(path, paint)

        path.reset()
        //画对应的6个值  从左边开始 逆时针开始画
        //在fill设置的时候，还是要path reset一下，不然会出现原来的path也会fill
        paint.style = Paint.Style.FILL
        //有点透明的绿色
        paint.color = Color.argb(0x77, 0, 0xff, 0)

        path.moveTo(pointX - (radius * progressPercents[0]), pointY)


        path.lineTo(
            pointX - (radius * sin(arc30) * progressPercents[1]),
            pointY + radius * cos(arc30) * progressPercents[1]
        )
        path.lineTo(
            pointX + (radius * sin(arc30) * progressPercents[2]),
            pointY + radius * cos(arc30) * progressPercents[2]
        )

        path.lineTo(
            pointX + (radius * progressPercents[3]), pointY
        )

        path.lineTo(
            pointX + radius * sin(arc30) *progressPercents[4],
            pointY - radius * cos(arc30)*progressPercents[4]
        )

        path.lineTo(
            pointX - radius * sin(arc30) *progressPercents[5],
            pointY - radius * cos(arc30)*progressPercents[5]
        )

        path.close()

        canvas.drawPath(path, paint)
        //不reset，后面全是红色
//        paint.reset()

        paint.color = Color.RED
        val fl = 15f

        canvas.drawCircle(pointX - (radius * progressPercents[0]), pointY, fl, paint)
        canvas.drawCircle(pointX - (radius * sin(arc30) * progressPercents[1]),
            pointY + radius * cos(arc30) * progressPercents[1], fl, paint)
        canvas.drawCircle(pointX + (radius * sin(arc30) * progressPercents[2]),
            pointY + radius * cos(arc30) * progressPercents[2], fl, paint)
        canvas.drawCircle(pointX + (radius * progressPercents[3]), pointY, fl, paint)
        canvas.drawCircle(pointX + radius * sin(arc30) *progressPercents[4],
            pointY - radius * cos(arc30)*progressPercents[4], fl, paint)
        canvas.drawCircle(pointX - radius * sin(arc30) *progressPercents[5],
            pointY - radius * cos(arc30)*progressPercents[5], fl, paint)

    }


}