package com.example.developlibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 作者：wl on 2017/9/14 16:14
 * 邮箱：wangl@ixinyongjia.com
 */
public class NetworkUtil {
    /**
     * 判断网络连接状态
     *
     * @return 返回true为连接
     */
    public static boolean isNetworkerConnect() {
        return getNetworkerStatus() != -1;
    }

    /**
     * 获取网络状态
     *
     * @return 1为wifi连接，2为2g网络，3为3g网络，-1为无网络连接
     */
    public static int getNetworkerStatus() {
        ConnectivityManager conMan = (ConnectivityManager) UiUtil.getContext().getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conMan.getActiveNetworkInfo();
        if (null != info && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                switch (info.getSubtype()) {
                    case 1:
                    case 2:
                    case 4:
                        // 2G网络
                        return 2;
                    default:
                        // 3G及其以上网络
                        return 3;
                }
            } else {
                // wifi网络
                return 1;
            }
        } else {
            // 无网络
            return -1;
        }
    }
}
