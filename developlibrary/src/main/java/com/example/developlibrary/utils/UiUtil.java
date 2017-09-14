package com.example.developlibrary.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.example.developlibrary.LibLoader;
import com.orhanobut.logger.Logger;


/**
 * 作者：wl on 2017/9/13 16:09
 * 邮箱：wangl@ixinyongjia.com
 */

//类作用描述：1.提供常见的获取屏幕宽高、获取各种资源等方法
//            2.px  dp转换
//            3.提供延时处理的方法
public class UiUtil {

    //手机状态栏的高度
    private static int stateBarHeight;
    //屏幕的高度
    private static int screenHeight;
    //屏幕的宽度
    private static int screenWidth;
    //虚拟导航栏的高度
    private static int navigation_bar_height;

    private static void init(Context context) {

        //获取状态栏的高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            stateBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }

        //获取屏幕的高度和宽度
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
        screenHeight = wm.getDefaultDisplay().getHeight();

        //获取底部导航栏的高度
        int navigationId = 0;
        int rid = getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        if (rid > 0) {
            navigationId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            navigation_bar_height = getResources().getDimensionPixelSize(navigationId);
        }

        Logger.i("screenHeight = %d, screenWidth = %d, stateBarHeight = %d, navigation_bar_height = %d",
                screenHeight, screenWidth, stateBarHeight, navigation_bar_height);
    }

    static {
        init(getContext());
    }

    public static Context getContext() {
        return LibLoader.getApplication();
    }


    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * @return 获取导航栏高度
     */
    public static int getNavigation_bar_height() {
        return navigation_bar_height;
    }

    /**
     * @return 状态栏高度
     */
    public static int getStateBarHeight() {
        return stateBarHeight;
    }

    /**
     * @return 获取屏幕高度
     */
    public static int getScreenHeight() {
        return screenHeight;
    }

    /**
     * @return 获取屏幕宽度
     */
    public static int getScreenWidth() {
        return screenWidth;
    }


    public static long getMainThreadId() {
        return LibLoader.getMainThreadId();
    }

    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * px转换dip
     */
    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getHandler() {
        return LibLoader.getMainThreadHandler();
    }

    /**
     * 延时在主线程执行runnable
     */
    public static boolean postDelayed(Runnable runnable, long delayMillis) {
        return getHandler().postDelayed(runnable, delayMillis);
    }

    /**
     * 在主线程执行runnable
     */
    public static boolean post(Runnable runnable) {
        return getHandler().post(runnable);
    }

    /**
     * 从主线程looper里面移除runnable
     */
    public static void removeCallbacks(Runnable runnable) {
        getHandler().removeCallbacks(runnable);
    }

    /**
     * 加载布局
     */
    public static View inflate(int resId) {
        return LayoutInflater.from(getContext()).inflate(resId, null);
    }


    /**
     * 获取文字
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 获取文字数组
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 获取dimen
     */
    public static int getDimens(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /**
     * 获取drawable
     */
    public static Drawable getDrawable(int resId) {
        Drawable drawable = getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        return drawable;
    }

    /**
     * 获取颜色
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }


    //判断当前的线程是不是在主线程
    public static boolean isRunInMainThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }

    public static void runInMainThread(Runnable runnable) {
        if (isRunInMainThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    /**
     * 判断触点是否落在该View上
     */
    public static boolean isTouchInView(MotionEvent ev, View v) {
        if (ev == null || v == null) {
            return false;
        }

        int[] vLoc = new int[2];
        v.getLocationOnScreen(vLoc);
        float motionX = ev.getRawX();
        float motionY = ev.getRawY();
        return motionX >= vLoc[0] && motionX <= (vLoc[0] + v.getWidth()) && motionY >= vLoc[1] && motionY <= (vLoc[1] + v.getHeight());
    }

}
