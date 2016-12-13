package com.fashare.hover_view;

/**
 * Created by jinliangshan on 16/12/13.
 */
public enum ViewState {
    FILL,       // 全屏
    HOVER,      // 半空悬停
    ICE_BERG,   // 冰山一角(只露出一点): 也是悬停, 比 HOVER 更低
    CLOSE;      // 关闭: 完全藏在屏幕底部

    private static int sRangeOfTop; // 指定全屏的大小
    private int mTop;   // FILL, HOVER... 各自对应高度: 即 View.getTop() 属性

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
