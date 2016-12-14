package com.fashare.hover_view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import android.support.v4.widget.DrawerLayout;

/**
 * User: fashare(153614131@qq.com)
 * Date: 2016-12-12
 * Time: 21:16
 *
 * {@link HoverView} 的容器,
 * 类似于{@link DrawerLayout}
 */
public class HoverViewContainer extends FrameLayout implements ViewStateManager {
    private final String TAG = this.getClass().getSimpleName();
    private Context mContext;

    private ViewDragHelper mBottomDragger;
    private static final float TOUCH_SLOP_SENSITIVITY = 1.f;
    private DragCallback mBottomCallback = new DragCallback();

    private HoverView mBottomView;
    static HoverView EMPTY_VIEW;

    private class DragCallback extends ViewDragHelper.Callback{
        // 仅捕获 mBottomView
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == mBottomView;
        }

        // 控制边界, 防止 mBottomView 的头部超出边界
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            if(child == mBottomView){
                int newTop = top;
                newTop = Math.max(newTop, ViewState.FILL.getTop(mBottomView));
                return newTop;
            }
            return top;
        }

        // 手指释放的时候回调
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if (releasedChild == mBottomView) {
                int curTop = releasedChild.getTop();

                setClosestStateIfBetween(ViewState.FILL, ViewState.HOVER, curTop);
                setClosestStateIfBetween(ViewState.HOVER, ViewState.CLOSE, curTop);
            }
        }

        private void setClosestStateIfBetween(ViewState beginState, ViewState endState, int curTop){
            int beginTop = getTopOfState(beginState),
                    endTop = getTopOfState(endState);
            if(curTop >= beginTop && curTop <= endTop)
                changeState(curTop < (beginTop + endTop)/2? beginState: endState);
        }
    }

    int getTopOfState(ViewState viewState){
        return viewState.getTop(mBottomView);
    }

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

        EMPTY_VIEW = new HoverView(context);
        EMPTY_VIEW.setLayoutParams(new FrameLayout.LayoutParams(0, 0));
    }

    private int mMeasuredHeight = 0;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if(mMeasuredHeight == 0){
            mMeasuredHeight = getMeasuredHeight();

            initViewState(mMeasuredHeight);
        }
    }

    private void initViewState(int height) {

        // 隐藏 mBottomView 到底部
        // 手动设置 marginTop: onLayout() 走完后相当于 View.setTop();
        mViewState = ViewState.CLOSE;
        ViewGroup.LayoutParams lp = mBottomView.getLayoutParams();
        if(lp instanceof MarginLayoutParams){
//            mBottomView.setTop(height);
            ((MarginLayoutParams)lp).setMargins(0, height, 0, 0);
        }

        // 重置 mBottomView 高度 = 父容器(this) 的高度
        lp.height = height;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mBottomView = findHoverView();
        if(mBottomView == EMPTY_VIEW)
            this.addView(EMPTY_VIEW);
    }

    private HoverView findHoverView() {
        for(int i=0; i<getChildCount(); i++)
            if(getChildAt(i) instanceof HoverView)
                return (HoverView)getChildAt(i);

        return EMPTY_VIEW;
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

    // ------ 滑动部分 begin ------
    private void smoothScrollTo(int finalTop){
        mBottomDragger.smoothSlideViewTo(mBottomView, 0, finalTop);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    // smoothScrollTo() 中用到 mScroller,
    // 不要忘了配合 computeScroll()
    @Override
    public void computeScroll() {
        if(mBottomDragger.continueSettling(true))
            ViewCompat.postInvalidateOnAnimation(this);
    }

    private void scrollTo(int finalTop){
        mBottomView.setTop(finalTop);
        ViewCompat.postInvalidateOnAnimation(this);
    }
    // ------ 滑动部分 end ------

    // ------ 对外接口 ViewStateManager: begin ------
    private ViewState mViewState = ViewState.CLOSE;

    @Override
    public void changeState(ViewState viewState){
        changeState(viewState, true);
    }

    @Override
    public void changeState(ViewState viewState, boolean isSmoothScroll) {
        mViewState = viewState;
        if(isSmoothScroll)
            smoothScrollTo(getTopOfState(viewState));
        else
            scrollTo(getTopOfState(viewState));
    }

    @Override
    public ViewState getState() {
        return mViewState;
    }
    // ------ 对外接口 ViewStateManager: end ------
}
