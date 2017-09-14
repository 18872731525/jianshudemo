package com.example.developlibrary.utils;

import com.orhanobut.logger.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 作者：wl on 2017/9/14 16:12
 * 邮箱：wangl@ixinyongjia.com
 */
public class DateUtil {
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";

    public static final String SERVCE_DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";

    /**
     * 将毫秒值转换为指定格式的字符串
     *
     * @param : @param time	毫秒值
     * @param : @param fomat	格式
     * @return type:String	返回格式化后的字符串
     * @author hujie
     */
    public static String getFormatString(long time, String fomat) {
        String formatString = "";
        if (fomat != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(fomat);
            formatString = dateFormat.format(new Date(time));
        }
        return formatString;
    }

    /**
     * 将字符串转换为毫秒值
     *
     * @param : @param date	日期字符串
     * @param : @param format	日期字符串的格式
     * @return type:long	日期字符串对应的毫秒值
     * @author hujie
     */
    public static long getFormatTime(String date, String format) {
        long time = 0;
        if (date != null && format != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            try {
                Date parse = dateFormat.parse(date);
                time = parse.getTime();
            } catch (ParseException e) {
                Logger.e(e, "日期格式转换错误");
            }
        }
        return time;
    }


    /***
     * 得到系统时间
     * @return
     */
    public static String getSysTime(String format) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String sysNewTime = sdf.format(calendar.getTime());
        return sysNewTime;
    }

    public static String getSysTime() {
        return getSysTime(DATE_FORMAT);
    }
}
