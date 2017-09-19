package com.example.dell.jianshudemo.mvp.function;

import android.app.Application;

import com.example.developlibrary.LibLoader;

/**
 * 作者：wl on 2017/9/14 17:35
 * 邮箱：wangl@ixinyongjia.com
 */
public class AppLoader extends Application {
    private static AppLoader mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        LibLoader.init(this);
    }

    public static AppLoader getInstance() {
        return mInstance;
    }
}
