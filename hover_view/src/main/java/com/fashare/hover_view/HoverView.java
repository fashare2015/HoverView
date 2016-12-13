package com.fashare.hover_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * User: fashare(153614131@qq.com)
 * Date: 2016-12-12
 * Time: 21:16
 */
public class HoverView extends LinearLayout implements IShowState{
    public static final float TOP_CLOSE = 1.0f;
    private final String TAG = this.getClass().getSimpleName();

    private Context mContext;

    float mTopFill = 0.0f, mTopHover = 0.4f, mTopIceBerg = 1.0f;

    private HoverViewContainer getContainer(){
        if(this.getParent() instanceof HoverViewContainer)
            return (HoverViewContainer)this.getParent();
        return null;
    }

    public HoverView(Context context) {
        super(context);
        init(context);
        initAttrs(context, null);
    }

    public HoverView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initAttrs(context, attrs);
    }

    public HoverView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initAttrs(context, attrs);
    }

    private void init(Context context) {
        mContext = context;
    }

    protected void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HoverView);
        mTopFill = ta.getFloat(R.styleable.HoverView_mTopFill, mTopFill);
        mTopHover = ta.getFloat(R.styleable.HoverView_mTopHover, mTopHover);
        mTopIceBerg = ta.getFloat(R.styleable.HoverView_mTopIceBerg, mTopIceBerg);
        ta.recycle();
    }

    // ------ 对外接口 IShowState: begin ------
    @Override
    public void changeState(ViewState viewState){
        changeState(viewState, true);
    }

    @Override
    public void changeState(ViewState viewState, boolean isSmoothScroll) {
        if(getContainer() != null)
            getContainer().changeState(viewState, isSmoothScroll);
    }

    @Override
    public ViewState getState() {
        if(getContainer() != null)
            return getContainer().getState();
        return ViewState.CLOSE;
    }
    // ------ 对外接口 IShowState: end ------
}
