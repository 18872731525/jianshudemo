package com.example.dell.jianshudemo.mvp.function.launch;

import android.app.Application;

import com.example.developlibrary.LibLoader;
import com.tencent.smtt.sdk.QbSdk;

/**
 * 作者：wl on 2017/9/14 17:35
 * 邮箱：wangl@ixinyongjia.com
 */
public class AppLoader extends Application {
    private static AppLoader mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        LibLoader.init(this);
        initX5();

    }

    public static AppLoader getInstance() {
        return mInstance;
    }

    private  void initX5() {
        QbSdk.initX5Environment(this,null);
        if (!QbSdk.isTbsCoreInited()) {
            // preinit只需要调用一次，如果已经完成了初始化，那么就直接构造view
            QbSdk.preInit(this, null);// 设置X5初始化完成的回调接口
        }
    }

}
