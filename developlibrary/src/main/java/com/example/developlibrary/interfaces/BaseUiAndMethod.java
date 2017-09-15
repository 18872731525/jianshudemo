package com.example.developlibrary.interfaces;

import android.app.Activity;
import android.support.annotation.Nullable;

import com.example.developlibrary.view.defaultview.DefaultView;
import com.example.developlibrary.view.statebar.StateBar;
import com.example.developlibrary.view.titlebar.BaseTitleBar;

/**
 * 作者：wl on 2017/9/15 10:53
 * 邮箱：wangl@ixinyongjia.com
 */


//接口说明：定义界面（activity或者fragment）中常用的方法以及页面公共的View 比如头部导航栏TitleBarView，状态栏StateBarView，缺省页showDefaultView
public interface BaseUiAndMethod {
    //无网络弹提示
    boolean isNetworkerConnectHint();

    //获取宿主Activity
    Activity getActivity();

    //显示隐藏加载中
    void showLoadingView();

    void hideLoadingView();

    //显示、隐藏缺省页
    void showDefaultView();

    void hideDefaultView();

    boolean defaultViewIsShow();

    //导航栏
    BaseTitleBar getTitleBar();

    //状态栏
    StateBar getStateBar();

    DefaultView.DefaultViewBuild getDefaultViewBuild();

    //提示相关方法
    void showHintAndFinish(String hint);

    void showHint(@Nullable String hintText);

    void showHint(String hintText, int color);
}
