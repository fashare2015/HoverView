package com.fashare.hover_view;

/**
 * Created by apple on 16-12-13.
 */
public interface IShowState {
    void changeState(ViewState viewState);

    void changeState(ViewState viewState, boolean isSmoothScroll);

    ViewState getState();
}
