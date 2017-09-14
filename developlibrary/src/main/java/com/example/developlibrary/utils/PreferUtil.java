package com.example.developlibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 作者：wl on 2017/9/14 17:08
 * 邮箱：wangl@ixinyongjia.com
 */
public class PreferUtil {
    private static SharedPreferences mPrefer;    //SharedPreferences对象
    private static final String APP_NAME = BasePackageUtil.getPackageName();    //保存数据的文件名

    static {
        init();
    }

    /**
     * 初始化SharedPreferences
     */
    private static void init() {
        //实例化SharedPreferences
        mPrefer = UiUtil.getContext().getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 返回的是true，表示传入的字符串是否包含知道的char值序列，
     */
    public static boolean contains(String key) {
        return mPrefer.contains(key);
    }

    /**
     * 保存字符串类型的数据
     */
    public static void putString(String key, String value) {
        SharedPreferences.Editor edit = mPrefer.edit();    //实例化SharedPreferences.Editor对象
        edit.putString(key, value == null ? "" : value);    //保存键值
        edit.apply();    //提交数据   apply提交异步提交 无返回结果  commit同步，有结果
    }

    /**
     * 取得字符类型的数据
     */
    public static String getString(String key, String value) {
        return mPrefer.getString(key, value);
    }

    /**
     * 保存整型类型的数据
     */
    public static void putInt(String key, int value) {
        SharedPreferences.Editor edit = mPrefer.edit();
        edit.putInt(key, value);
        edit.apply();
    }

    /**
     * 获取整型类型的数据
     */
    public static int getInt(String key, int value) {
        return mPrefer.getInt(key, value);
    }

    /**
     * 保存布尔类型的数据
     */
    public static void putBoolean(String key, boolean value) {
        mPrefer.edit().putBoolean(key, value).apply();
    }

    /**
     * @param key
     */
    public static boolean getBoolean(String key, boolean defValue) {
        return mPrefer.getBoolean(key, defValue);
    }

    /**
     * 保存long型的数据
     */
    public static void putLong(String key, long value) {
        mPrefer.edit().putLong(key, value).apply();
    }

    /**
     * 取得long型的数据
     */
    public static long getLong(String key, long value) {
        return mPrefer.getLong(key, value);
    }

    /**
     * 删除key对应的value
     *
     * @param key
     */
    public static void removeKey(String key) {
        mPrefer.edit().remove(key).apply();
    }
}
