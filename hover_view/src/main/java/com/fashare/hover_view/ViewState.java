package com.fashare.hover_view;

/**
 * Created by jinliangshan on 16/12/13.
 *
 * {@link HoverView} 的状态
 */
public enum ViewState {
    FILL{
        @Override
        int getTop(HoverView hoverView) {
            return getTopByScale(hoverView, hoverView.getTopFill());
        }
    },       // 全屏

    HOVER{
        @Override
        int getTop(HoverView hoverView) {
            return getTopByScale(hoverView, hoverView.getTopHover());
        }
    },      // 半空悬停

    CLOSE{
        @Override
        int getTop(HoverView hoverView) {
            return getTopByScale(hoverView, HoverView.TOP_CLOSE);
        }
    };      // 关闭: 完全藏在屏幕底部

    /**
     * FILL, HOVER... 各自状态对应高度: 即 View.getTop() 属性
     * @param hoverView 指定的 hoverView
     * @return
     */
    abstract int getTop(HoverView hoverView);

    private static int getTopByScale(HoverView hoverView, float scale){
        if(hoverView.getContainer() != null)
            return (int)(scale * hoverView.getContainer().getMeasuredHeight());
        return 0;
    }
}
