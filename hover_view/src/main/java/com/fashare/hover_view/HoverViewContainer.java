package com.fashare.hover_view;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * User: fashare(153614131@qq.com)
 * Date: 2016-12-12
 * Time: 21:16
 * <br/><br/>
 */
public class HoverViewContainer extends FrameLayout implements IShowState{
    private final String TAG = this.getClass().getSimpleName();

    private Context mContext;

    private ViewDragHelper mBottomDragger;
    private static final float TOUCH_SLOP_SENSITIVITY = 1.f;
    private ViewDragHelper.Callback mBottomCallback = new ViewDragHelper.Callback() {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
//            Log.d(TAG, "tryCaptureView");
            return true;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return top;
        }

        //手指释放的时候回调
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            //mAutoBackView手指释放时可以自动回去
            if (releasedChild == mBottomView) {
                mBottomDragger.settleCapturedViewAt(0, 100);
                invalidate();
            }
        }

        //在边界拖动时回调
        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
//            mDragger.captureChildView(mEdgeTrackerView, pointerId);
        }
    };

    private View mBottomView;
    private static View EMPTY_VIEW;


    public HoverViewContainer(Context context) {
        super(context);
        init(context);
    }

    public HoverViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HoverViewContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mBottomDragger = ViewDragHelper.create(this, TOUCH_SLOP_SENSITIVITY, mBottomCallback);
        mBottomDragger.setEdgeTrackingEnabled(ViewDragHelper.EDGE_BOTTOM);


        EMPTY_VIEW = new View(context);



//        initScroller(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        for(int i=0; i<getChildCount(); i++)
            if(getChildAt(i) instanceof HoverView)
                mBottomView = getChildAt(i);

        if(mBottomView == null)
            mBottomView = EMPTY_VIEW;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mBottomDragger.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mBottomDragger.processTouchEvent(ev);
        return true;
    }

//    // ------ 滑动部分 begin ------
//    private Scroller mScroller;
//    private int mScrollDuration = 1000; // 单位 ms
//
//    private void initScroller(Context context) {
//        mScroller = new Scroller(context);
//    }
//
//    private void smoothScrollTo(int destY){
//        int scrollY = mBottomView.getScrollY();
//        int deltaY = destY - scrollY;
//        mScroller.startScroll(0, scrollY, 0, deltaY, mScrollDuration);
//        mBottomView.invalidate();
//    }
//
//    @Override
//    public void computeScroll() {
//        if(mScroller.computeScrollOffset()){
//            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
//            mBottomView.postInvalidate();
//        }
//    }
//    // ------ 滑动部分 end ------
    @Override
    public void computeScroll() {
        if(mBottomDragger.continueSettling(true))
            invalidate();
    }

    // ------ 对外接口 IShowState: begin ------
    @Override
    public void showAsHover(){
//        ((HoverView)mBottomView).showAsHover();
//        mBottomView.scrollTo(0, -300);
        mBottomDragger.smoothSlideViewTo(mBottomView, 0, 100);
        invalidate();
    }
    // ------ 对外接口 IShowState: end ------
}