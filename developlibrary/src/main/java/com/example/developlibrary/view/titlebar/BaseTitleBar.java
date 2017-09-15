package com.example.developlibrary.view.titlebar;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.View;

/**
 * 作者：wl on 2017/9/15 11:37
 * 邮箱：wangl@ixinyongjia.com
 */

//接口说明：基础的导航栏接口，实例化后必须实现的方法（好处：方便管理，统一定义方法，可以实例化多种不同风格导航栏）
public interface BaseTitleBar {
    View getView();

    /*设置整体背景色*/
    BaseTitleBar setBgColor(int color);

    /*标题栏相关*/
    BaseTitleBar setTitle(@StringRes int StringResId);

    BaseTitleBar setTitle(String text);

    BaseTitleBar setTitleIcon(@DrawableRes int drawableId);

    BaseTitleBar setTitleTextColor(int color);


    /*右侧文本或按钮相关*/
    BaseTitleBar setRightText(String text);

    BaseTitleBar setRightText(@StringRes int stringResId);

    BaseTitleBar setRightIcon(@DrawableRes int drawableId);

    BaseTitleBar showRightTextView();

    BaseTitleBar hideRightTextView();

    BaseTitleBar setRightTextColor(int color);

    BaseTitleBar setRightTextClickListener(View.OnClickListener listener);


    /*左侧文本或按钮相关*/
    BaseTitleBar setLeftText(String text);

    BaseTitleBar setLeftText(@StringRes int stringResId);

    BaseTitleBar setLeftIcon(@DrawableRes int drawableId);

    BaseTitleBar showLeftTextView();

    BaseTitleBar hideLeftTextVeiw();

    BaseTitleBar setLeftTextColor(int color);

    BaseTitleBar setLeftTextClickListener(View.OnClickListener listener);

    int getId();
}
