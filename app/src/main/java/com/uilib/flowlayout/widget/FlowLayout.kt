package com.uilib.flowlayout.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.collection.ArrayMap

/**
 * @author  zhousaito
 * @date  2020/7/27 16:51
 * @version 1.0
 * @Dec 略
 *
 *  1。onMeasure  -》 根据策略，先调用 measureChildWithMargins/measureChild
 *                   通过子类测量好后，child.getMeasureHeight/Width进行策略计算
 *                   确定 当前 ViewGroup 的 宽 和 高
 *
 *                   同时可以保持，根据策略计算好的每个View 所需要的状态。
 *
 *  2. onLayout  -》 根据策略进行布局，确定子View排放的，位置，当然也可以确定子View的大小，
 *                   一般不会自由发挥【这里child.layout()可以计算错误，进行显示，当然特殊情况，自己重新计算
 *                   不影响 需求 ， 我感觉是可以的，毕竟不会这么死板，一定按照固定思路来 】
 *
 */
class FlowLayout
@JvmOverloads
constructor(context: Context, set: AttributeSet? = null, defAttr: Int = 0) :
    ViewGroup(context, set, defAttr) {


    //下面两个容器，记录onMeasure中计算好的值
    //然后在Layout中进行重新拿取出来，这样onLayout的时候，就不在重新根据策略计算了
    /**
     * Int 第几行
     * List<View> 第几行有多少个View
     */
    val arrayMap = ArrayMap<Int, List<View>>()

    /**
     * 每行的高度
     */
    val arrayHeight = ArrayList<Int>()


    companion object {
        const val TAG = "FlowLayout"
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        arrayMap.clear()
        arrayHeight.clear()

        //todo 宽度就用外面给的空间
        var width = widthSize
        var height = 0

        val heightMeasure = heightMeasure(widthMeasureSpec, heightMeasureSpec, width)


        when (heightMode) {
            MeasureSpec.EXACTLY -> {
                height = heightSize
            }
            MeasureSpec.UNSPECIFIED,
            MeasureSpec.AT_MOST -> {
                //测量的子View的高度
                height = heightMeasure
            }
        }

        setMeasuredDimension(width, height)
    }

    /**
     * 根据自己策略，测量出高度
     * todo 顺便可以记录一下，当layout的会后就可以直接拿出来使用了
     */
    private fun heightMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int, widthSize: Int): Int {
        // 1. 测量子View
        // 当前测量宽度和width进行对比，如果width超过宽度了
        // 就换下一行
        var currentWidth = 0
        // 下一行的高度差值是，当前行最高的
        var currentHeight = 0
        // 记录当前行最高的高度
        var currentMaxHeight = 0

        var list = ArrayList<View>()

        for (i in 0 until childCount) {

            val view = getChildAt(i)
            measureChildWithMargins(view, widthMeasureSpec, 0, heightMeasureSpec, 0)
            val lp = view.layoutParams as MarginLayoutParams
            val viewWidth = view.measuredWidth + lp.leftMargin + lp.rightMargin
            val viewHeight = view.measuredHeight + lp.topMargin + lp.bottomMargin
            //如果继续添加的时候
            //大于了当前测量的FlowLayout的宽度
            //就换一行
            if (currentWidth + viewWidth > widthSize) {
                currentHeight += currentMaxHeight

                //记录行数的所有View
                arrayMap[arrayHeight.size] = list
                //下次循环重新创建
                list = ArrayList<View>()
                arrayHeight.add(currentMaxHeight)

                currentWidth = 0
                currentMaxHeight = 0
            }

            currentWidth += viewWidth

            //比较，记录当前最大高度
            if (currentMaxHeight < viewHeight) {
                currentMaxHeight = viewHeight
            }

            list.add(view)

            //最后一个，没有换行
            if (currentWidth + viewWidth <= widthSize && i == childCount - 1) {
                currentHeight += currentMaxHeight

                //记录行数的所有View
                arrayMap[arrayHeight.size] = list
                //下次循环重新创建
                arrayHeight.add(currentMaxHeight)
            }
        }

        return currentHeight
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

//        var left = 0
//        var top = 0
//        var curMaxHeight = 0
//        for (i in 0 until childCount) {
//            val view = getChildAt(i)
//            val lp = view.layoutParams as MarginLayoutParams
//            val viewWidth = view.measuredWidth + lp.leftMargin + lp.rightMargin
//            val viewHeight = view.measuredHeight + lp.topMargin + lp.bottomMargin
//            if (curMaxHeight < viewHeight) {
//                curMaxHeight = viewHeight
//            }
//
//            if (left + viewWidth > measuredWidth) {
//                left = 0
//                top += curMaxHeight
//                curMaxHeight = 0
//
//                Log.e(TAG, "---------- >")
//            }
//
//            view.layout(left, top, viewWidth + left, viewHeight + top)
//            Log.e(TAG, "$left, $top, $viewWidth, $viewHeight")
//            left += viewWidth
//        }


        //左边坐标记录
        var left = 0
        //顶部坐标记录
        var top = 0
        var heightIndex = 0
        for (item in arrayMap) {
            val viewList = item.value
            for (view in viewList) {
                val lp = view.layoutParams as MarginLayoutParams
                val viewWidth = view.measuredWidth + lp.leftMargin + lp.rightMargin
                val viewHeight = view.measuredHeight + lp.topMargin + lp.bottomMargin
                view.layout(left, top, viewWidth + left, viewHeight + top)
                left += viewWidth
            }
            //左边记录换行时清零
            left = 0
            //头部开始换行累加，原来测量的记录中缓存中去拿取
            top += arrayHeight.get(heightIndex)
            //换下一行
            heightIndex++
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.e(TAG, "----onDraw----")
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(MarginLayoutParams.WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT)
    }
}