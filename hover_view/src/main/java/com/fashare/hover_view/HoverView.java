package com.fashare.hover_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * User: fashare(153614131@qq.com)
 * Date: 2016-12-12
 * Time: 21:16
 * <br/><br/>
 */
public class HoverView extends LinearLayout implements IShowState{
    private final String TAG = this.getClass().getSimpleName();

    private Context mContext;


    private static View EMPTY_VIEW;


    public HoverView(Context context) {
        super(context);
        init(context);
    }

    public HoverView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HoverView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        EMPTY_VIEW = new View(context);

        initScroller(context);
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return mBottomDragger.shouldInterceptTouchEvent(ev);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        mBottomDragger.processTouchEvent(ev);
//        return true;
//    }

    // ------ 滑动部分 begin ------
    private Scroller mScroller;
    private int mScrollDuration = 1000; // 单位 ms

    private void initScroller(Context context) {
        mScroller = new Scroller(context);
    }

    private void smoothScrollTo(int destY){
        int scrollY = getScrollY();
        int deltaY = destY - scrollY;
        mScroller.startScroll(0, scrollY, 0, deltaY, mScrollDuration);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
    // ------ 滑动部分 end ------


    // ------ 对外接口 IShowState: begin ------
    @Override
    public void showAsHover(){
        smoothScrollTo(100);
    }
    // ------ 对外接口 IShowState: end ------
}