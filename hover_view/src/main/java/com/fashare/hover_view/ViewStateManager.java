package com.fashare.hover_view;

/**
 * Created by apple on 16-12-13.
 *
 * {@link HoverView} 的控制接口,
 * 根据 {@link ViewState} 进行控制
 */
public interface ViewStateManager {

    /**
     * 根据 viewState,
     * 切换 {@link HoverView} 的状态, 带动画:
     *
     * 1. {@link ViewState#FILL}:   全屏
     * 2. {@link ViewState#HOVER}:  悬停
     * 3. {@link ViewState#CLOSE}:  关闭(隐藏)
     *
     * @param viewState 给定的 {@link ViewState}
     */
    void changeState(ViewState viewState);

    /**
     * 由 isSmoothScroll 控制是否带有动画,
     * 其余同 {@link #changeState(ViewState)}
     *
     * @param viewState 给定的 {@link ViewState}
     * @param isSmoothScroll 是否需要动画
     */
    void changeState(ViewState viewState, boolean isSmoothScroll);

    /**
     * 获取当前状态
     *
     * @return 当前状态
     */
    ViewState getState();
}
