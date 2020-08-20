package com.uilib.touchevent.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class HorizontalScrollViewEx extends ViewGroup {

    private Scroller scroller;
    private VelocityTracker velocityTracker;

    public HorizontalScrollViewEx(Context context) {
        this(context, null);
    }

    public HorizontalScrollViewEx(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalScrollViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        scroller = new Scroller(getContext());
        velocityTracker = VelocityTracker.obtain();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0;
        int height = 0;
//        int i = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.UNSPECIFIED);
//        measureChildren(i, heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);
        width = getChildAt(0).getMeasuredWidth();
        height = getChildAt(0).getMeasuredHeight();
//        for (int i = 0; i < getChildCount(); i++) {
//            View view = getChildAt(i);
//            measureChild(view, widthMeasureSpec, heightMeasureSpec);
//            width = view.getMeasuredWidth();
//            height = Math.max(height, view.getMeasuredHeight());
//        }

        //width如果EXACTLY的话，会出现第二个listView显示不全，如果做滑动布局的话
        setMeasuredDimension(getChildCount() * width,
                heightMode == MeasureSpec.EXACTLY ? heightSize : height);
//        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : getChildCount() * width,
//                heightMode == MeasureSpec.EXACTLY ? heightSize : height);
    }

    private int mChildWidth;
    private int mChildIndex;
    private int mChildSize;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = 0;
//        int height = 0;

        mChildSize = getChildCount();

        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view.getVisibility() != View.GONE) {
                view.layout(l + width, t, view.getMeasuredWidth() + width, view.getMeasuredHeight());
                width += view.getMeasuredWidth();
                mChildWidth = view.getMeasuredWidth();
            }
//            height = Math.max(height, view.getMeasuredHeight());
        }
    }

    float lastX = 0;
    float lastY = 0;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isIntercept = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isIntercept = false;
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                    //继续拦截的操作
                    isIntercept = true;
                }
                lastX = ev.getX();
                lastY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float x = lastX - ev.getX();
                float y = lastY - ev.getY();
                if (Math.abs(x) - Math.abs(y) > 0) {
                    isIntercept = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        lastX = ev.getX();
        lastY = ev.getY();
        return isIntercept;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //添加进去
        velocityTracker.addMovement(ev);

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }

                lastX = ev.getX();
                lastY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float x = ev.getX() - lastX;
//                float y = ev.getY() - lastY;
                scrollBy((int) -x, 0);
                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                int scrollToChildIndex = scrollX / mChildWidth;
                velocityTracker.computeCurrentVelocity(1000);
                float xVelocity = velocityTracker.getXVelocity();
                if (Math.abs(xVelocity) > 50) {
                    mChildIndex = xVelocity > 0 ? mChildIndex - 1 : mChildIndex + 1;
                } else {
                    mChildIndex = (scrollX + mChildWidth / 2) / mChildWidth;
                }
                mChildIndex = Math.max(0, Math.min(mChildIndex, mChildSize - 1));
                int dx = mChildIndex * mChildWidth - scrollX;
                smoothScrollBy(dx, 0);
                velocityTracker.clear();
                break;
        }
        lastX = ev.getX();
        lastY = ev.getY();
        return true;
    }

    private void smoothScrollBy(int dx, int dy) {
        scroller.startScroll(getScrollX(), 0, dx, 0, 500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        velocityTracker.recycle();
        super.onDetachedFromWindow();
    }
}
