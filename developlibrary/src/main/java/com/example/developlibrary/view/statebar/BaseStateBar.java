package com.example.developlibrary.view.statebar;

import android.support.annotation.DrawableRes;
import android.view.View;

/**
 * 作者：wl on 2017/9/15 15:56
 * 邮箱：wangl@ixinyongjia.com
 */
public interface BaseStateBar {
    void show();

    void hide();

    void setBackgroundColor(int color);

    void setBackgroundDrawable(@DrawableRes int resId);

    View getView();

    int getId();

    boolean isEnabled();
}
