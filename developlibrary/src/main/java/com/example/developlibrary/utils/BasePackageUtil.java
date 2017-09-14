package com.example.developlibrary.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import java.util.List;

import static android.text.TextUtils.isEmpty;

/**
 * 作者：wl on 2017/9/14 11:24
 * 邮箱：wangl@ixinyongjia.com
 */

//类作用描述：提供app Package相关信息，判断包名x的应用是否安装，等等
public class BasePackageUtil {
    public static final String WECHAT_PACKAGENAME = "com.tencent.mm";
    public static final String QQ_MOBILE = "com.tencent.mobileqq";

    private static String packageName;

    /**
     * 根据packageName获取packageInfo
     */
    public static PackageInfo getPackageInfo(String packageName) {
        Context context = UiUtil.getContext();
        if (null == context) {
            return null;
        }
        if (isEmpty(packageName)) {
            packageName = context.getPackageName();
        }
        PackageInfo info = null;
        PackageManager manager = context.getPackageManager();
        // 根据packageName获取packageInfo
        try {
            info = manager.getPackageInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(e, "获取应用包信息异常");
        }
        return info;
    }

    /**
     * 获取本应用的VersionCode
     */
    public static String getVersionCode() {
        PackageInfo info = getPackageInfo(null);
        if (info != null) {
            return info.versionName;
        } else {
            return 1 + "";
        }
    }

    /**
     * 获取本应用的VersionName
     */
    public static String getVersionName() {
        PackageInfo info = getPackageInfo(null);
        if (info != null) {
            return info.versionName;
        } else {
            return null;
        }
    }

    /**
     * @param : [packageName] 应用的包名
     * @return type: boolean
     * @des: 判断手机有没有安装 传递包名的应用
     * @date 创建时间：2016/5/6 9:31
     * @author hujie
     */
    public static boolean isInstallApp(String packageName) {
        boolean isInstall = true;
        Context context = UiUtil.getContext();
        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(packageName, 0);
            PackageInfo packageInfo = pm.getPackageInfo(packageName, 0);
            if (applicationInfo == null || packageInfo == null) {
                isInstall = false;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            isInstall = false;
        }
        return isInstall;
    }

    /**
     * 获取应用的包名
     *
     * @return
     */
    public static String getPackageName() {
        if (packageName == null) {
            packageName = UiUtil.getContext().getPackageName();
        }
        return packageName;
    }


    public static boolean runningBackGround = false;

    /**
     * 程序是否在前台运行 //可以存在权限，和系统定制的问题，建议在主module中添加ActivityLifecycleCallbacks来判断
     *
     * @return
     */
    public static boolean isRunningForeground() {
        String packageName = getPackageName();
        String topActivityClassName = getTopActivityName();

        Logger.d("packageName = %s , topActivityClassName = %s", packageName, topActivityClassName);

        if (!TextUtils.isEmpty(topActivityClassName) && topActivityClassName.startsWith(packageName)) {
            Logger.d("在前台运行");
            runningBackGround = false;
            return true;
        } else {
            Logger.d("在后台运行");
            runningBackGround = true;
            return false;
        }
    }


    private static String getTopActivityName() {
        String topActivityClassName = "";
        ActivityManager activityManager =
                (ActivityManager) (UiUtil.getContext().getSystemService(Context.ACTIVITY_SERVICE));

        try {
            List<ActivityManager.RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(1);
            if (runningTaskInfos != null) {
                ComponentName f = runningTaskInfos.get(0).topActivity;
                topActivityClassName = f.getClassName();
            }
        } catch (Exception e) {
            Logger.e(e, "获取Activity任务栈错误");
        }

        return topActivityClassName;
    }
}
