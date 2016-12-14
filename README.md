#HoverView
一直觉得知乎的交互体验是很好的，这次山寨了一把。
这是一个底部抽屉，类似知乎收藏夹。它可以悬停在中间，随着滑动自然过渡到全屏。
它是仿照`support`包里的`DrawLayout`和`NavigationView`设计的。
<br/>

#效果图
知乎收藏夹：

- 可以悬停在中间
- 可以滑动到全屏
- 过渡十分流畅，纵享丝滑

![这里写图片描述](http://img.blog.csdn.net/20161214212420754?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTE1MzYxNDEzMQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

<br/>
我的 HoverView :

- 可以悬停在中间
- 可以滑动到全屏
- 过渡尚可，没有知乎的流畅

![这里写图片描述](http://img.blog.csdn.net/20161214213925928?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTE1MzYxNDEzMQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
<br/>

#CSDN链接
http://blog.csdn.net/a153614131/article/details/53647831
<br/>

# 特点及使用场景
底部抽屉，可以悬停、也可以全屏展示。适用于：

- 淘宝购物车
- 收藏夹
- 分享框
- ...

<br/>

#Gradle 依赖
最新版本：1.0.1
```java
dependencies {
    compile 'com.fashare2015:HoverView:最新版本'
}
```
<br/>

#使用方式
##布局结构
类似`support`包里的`DrawLayout`和`NavigationView`的关系。这里有两个`View`:

- `HoverViewContainer`: 容器，对应`DrawLayout`
- `HoverView`: 悬停抽屉，对应`NavigationView`

```xml
<com.fashare.hover_view.HoverViewContainer
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@android:color/darker_gray">

    // 原本的 rootView
    ...

    <com.fashare.hover_view.HoverView
        android:id="@+id/hv"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        app:mTopFill="0.0"
        app:mTopHover="0.5">

        // HoverView 的内容
        ...

    </com.fashare.hover_view.HoverView>

</com.fashare.hover_view.HoverViewContainer> 
```
<br/>

##HoverView 属性
它有两个属性，描述 `HoverView.getTop()`占父容器的比例（Height 为父容器高度）：

- app:mTopFill="0.0" 全屏时，距顶部 0.0Height（默认）
- app:mTopHover="0.5" 悬停时，距顶部 0.5Height
<br/>

##状态（高度）切换
```java
// 状态定义
public enum ViewState {
    FILL,       // 全屏
    HOVER,      // 半空悬停
    CLOSE;      // 关闭: 完全藏在屏幕底部
}

// 状态切换 —— 类似 View.setVisibility();
mHoverView.changeState(ViewState.HOVER);   // 打开至 "悬停" 状态
mHoverView.changeState(ViewState.FILL);    // 打开至 "全屏" 状态
mHoverView.changeState(ViewState.CLOSE);   // 切换至 "关闭" 状态
```
<br/>

#类图
![这里写图片描述](http://img.blog.csdn.net/20161214221551601?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYTE1MzYxNDEzMQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
<br/>

#最后
使用愉快～


