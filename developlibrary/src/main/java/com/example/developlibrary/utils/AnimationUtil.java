package com.example.developlibrary.utils;

/**
 * 作者：wl on 2017/9/20 16:44
 * 邮箱：wangl@ixinyongjia.com
 */
public class AnimationUtil {
    public static long getAnimationDuraing() {
        if (!PreferUtil.getBoolean(BaseConstant.FIRST_START, false)) {
            return 200;
        } else {
            return 200;
        }
    }
}
