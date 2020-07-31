package com.uilib.book.chapter1.widget2

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @author  zhousaito
 * @date  2020/7/30 11:31
 * @version 1.0
 * @Dec 略
 */
class RegionView @JvmOverloads
constructor(context: Context, attributeSet: AttributeSet? = null, defAttr: Int = 0) :
    View(context, attributeSet, defAttr) {

    val region = Region()
    val rect = Rect()
    val rectF = RectF()
    val paint = Paint().apply {
        setARGB(0x7f, 0, 0xff, 0)
        style = Paint.Style.FILL
    }

    val path = Path();

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

//        rect.set(100, 100, 200, 200)
//        region.set(rect)
//        region.set(100, 150, 250, 250)
//        region.set(200, 300, 300, 600)
//        val regionIterator = RegionIterator(region)
//        while (regionIterator.next(rect)) {
//            canvas.drawRect(rect, paint)
//            paint.setARGB(0x7f, 0xff, 0, 0)
//        }
//        rectF.set(100f, 100f, 500f, 300f)
//        path.addOval(rectF, Path.Direction.CCW)
////        canvas.drawPath(path, paint)
//
//        region.set(300, 100, 600, 200)
//        region.setPath(path, region)

        paint.style = Paint.Style.FILL


//        canvas.drawRect(region.bounds, paint)
        rectF.set(100f, 100f, 500f, 300f)
        path.addOval(rectF, Path.Direction.CCW)

        region.setPath(path, Region(100, 100, 500, 300))

        rect.set(100,100, 200, 500)
//        region.union(rect)
        region.op(rect, Region.Op.XOR)

        drawRegion(canvas, region, paint)
//        val regionIterator = RegionIterator(region)
//        while (regionIterator.next(rect)) {
//            canvas.drawRect(rect, paint)
//        }


    }

    private fun drawRegion(canvas: Canvas, region: Region, paint: Paint) {
        val regionIterator = RegionIterator(region)
        val rect1 = Rect()
        while (regionIterator.next(rect1)) {
            canvas.drawRect(rect1, paint)
        }
    }
}