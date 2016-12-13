package com.fashare.hover_view;

/**
 * Created by jinliangshan on 16/12/13.
 */

public enum ViewState {
    FILL,

    HOVER,

    ICE_BERG,

    CLOSE;

    private static int sRangeOfTop;
    private int mTop;

    public static void setRangeOfTop(int rangeOfTop) {
        sRangeOfTop = rangeOfTop;
    }

    public int getTop() {
        return mTop;
    }

    public void setTop(int top) {
        mTop = top;
    }

    private void setTopScale(float scale){
        setTop((int)(scale * sRangeOfTop));
    }

    public static void initViewState(final HoverView hoverView, final HoverViewContainer hoverViewContainer) {
        // getHeight() 为 0
        hoverView.post(new Runnable() {
            @Override
            public void run() {
                ViewState.setRangeOfTop(hoverViewContainer.getHeight());

                ViewState.FILL.setTopScale(hoverView.mTopFill);
                ViewState.HOVER.setTopScale(hoverView.mTopHover);
                ViewState.ICE_BERG.setTopScale(hoverView.mTopIceBerg);
                ViewState.CLOSE.setTopScale(HoverView.TOP_CLOSE);

                hoverViewContainer.changeState(ViewState.CLOSE, false);
                if(ViewState.ICE_BERG.getTop() < ViewState.CLOSE.getTop())
                    hoverViewContainer.changeState(ViewState.ICE_BERG, false);
            }
        });
    }
}
