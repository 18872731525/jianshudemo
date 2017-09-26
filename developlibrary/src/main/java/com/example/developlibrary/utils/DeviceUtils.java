package com.example.developlibrary.utils;

import android.Manifest;
import android.content.Context;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * 作者：wl on 2017/9/26 15:03
 * 邮箱：wangl@ixinyongjia.com
 */
public class DeviceUtils {
    public static String deviceId;

    public static String getDeviceId() {

        if (TextUtils.isEmpty(deviceId)) {
            try {
                android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) UiUtil.getContext().getSystemService(Context.TELEPHONY_SERVICE);

                if (EasyPermissions.hasPermissions(UiUtil.getContext(),Manifest.permission.READ_PHONE_STATE)) {
                    deviceId = tm.getDeviceId();
                }

            } catch (Exception e) {
                Logger.e("获取设备号异常");
            }

            if (TextUtils.isEmpty(deviceId)) {
                deviceId = android.provider.Settings.Secure.getString(UiUtil.getContext().getContentResolver(),
                        android.provider.Settings.Secure.ANDROID_ID);
            }
        }
        return deviceId;
    }


}
