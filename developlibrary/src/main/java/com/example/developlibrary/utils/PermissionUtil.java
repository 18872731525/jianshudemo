package com.example.developlibrary.utils;

import android.hardware.Camera;

/**
 * 作者：wl on 2017/9/14 15:11
 * 邮箱：wangl@ixinyongjia.com
 */
public class PermissionUtil {
    /**
     * 判断摄像头权限是否开启
     * true:权限开启 false:没有权限
     *
     * @return
     */
    public static boolean isCameraCanUse() {
        boolean canUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open(0);
            mCamera.setDisplayOrientation(90);
        } catch (Exception e) {
            canUse = false;
        }
        if (canUse) {
            mCamera.release();
            mCamera = null;
        }

        return canUse;
    }

    /*常规方法*/ //TODO
}
