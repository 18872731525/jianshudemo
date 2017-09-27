package com.example.developlibrary;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;

import java.util.Stack;

/**
 * 作者：wl on 2017/9/14 09:47
 * 邮箱：wangl@ixinyongjia.com
 */

//类作用描述：1.接收主module传递的上下文，提供给lib内部需要context的工具类使用
//            2.提供activity堆栈管理的方法
//            3.提供一个获取主线程id的方法以及mMainThreadHandler，可以处理一些延时操作等，后面考虑用evevbug替换 //TODO
public class LibLoader {
    private static Stack<Activity> activityStack;    //Activity的栈

    private static int mMainThreadId = -1;      //主线程ID

    private static Handler mMainThreadHandler;  //主线程Handler  //后面替换为evenbus

    private static Application mContext;    //应用全局的上下文

    public LibLoader() {

    }

    /**
     * 在自定义的Application初始化时调用, 初始化一些变量
     *
     * @param context 应用全局的上下文
     */
    public static void init(Application context) {
        mContext = context;
        init();

    }

    private static void init() {
        mMainThreadId = android.os.Process.myTid();
        mMainThreadHandler = new Handler();
//        initX5();
//        preinitX5WebCore();
    }

    public static Application getApplication() {
        return mContext;
    }

    /**
     * 获取主线程ID
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }


    /**
     * 把Activity添加到栈
     */
    public static void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 移除Activity
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 获取当前的Activity,栈中最后一个压入, 有可能为null
     *
     * @return
     */
    public static Activity getCurrentActivity() {
        if (activityStack != null && !activityStack.isEmpty()) {
            Activity activity = activityStack.lastElement();
            return activity;
        }
        return null;
    }

    /**
     * 结束指定的Activity
     */
    public static void finishActivity(Activity activity) {
        if (activity != null) {    //判断传入的Activity是否为空
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public static void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 判断指定类名的Activity是否已经存在
     *
     * @param cls
     * @return
     */
    public static boolean isExist(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 结束所有的Activity
     */
    public static void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {    //栈的每一项不为空
                activityStack.get(i).finish();//结束每一项
            }
        }
        activityStack.clear();
    }

}
