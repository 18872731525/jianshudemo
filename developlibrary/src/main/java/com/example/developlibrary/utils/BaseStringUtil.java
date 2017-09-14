package com.example.developlibrary.utils;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 作者：wl on 2017/9/15 11:24
 * 邮箱：wangl@ixinyongjia.com
 */
public class BaseStringUtil {

    public final static String FORMAT_ONE = "##,###,##0.00";
    public final static String FORMAT_TWO = "#,###,###";
    public final static String FORMAT_THREE = "##,###,###.##";
    /**
     * 正则表达式:验证密码
     */
    public static final String REGEX_PASSWORD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
    /**
     * 邮箱
     */
    public static final String REGEX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static final String USER_NAME = "^[\\u2E80-\\uFE4F][\\u2E80-\\uFE4F\\·]{0,8}[\\u2E80-\\uFE4F]$";

    public static final String ADDRESS = "^[\\u2E80-\\uFE4Fa-zA-Z0-9\\s\\-/()（）&,.，·#，。；;:：！!？?|｜、''“”’‘㙍]{0,}$";
    //特殊字符
    public static final String REGEX_STR = "[`~@#$%^&*+=|{}'',\\[\\]<>/~！@#￥%……&*（）——+|{}【】‘]";

    public static final String REGEX_STR_ACVICE = "[`~@#$%^&*+=|{}'',\\[\\]<>/~！@#￥%……&*（）——+|{}【】‘]";

    /**
     * 判断两个字符串是否相同
     *
     * @param s1
     * @param s2
     * @return Matcher
     */
    public static boolean isEquals(String s1, String s2) {
        if (isEmpty(s1)) {
            s1 = "";
        }

        if (isEmpty(s2)) {
            s2 = "";
        }
        return s1.equals(s2);
    }


    /**
     * 判断字符串是否为null
     *
     * @param text 字符串
     * @return true:为null  false:不为null
     */
    public static boolean isEmpty(String text) {

        return TextUtils.isEmpty(text) || "null".equals(text);
    }

    /**
     * 校验特俗字符
     *
     * @param str
     * @return 校验通过返回true，否则返回false
     */
    public static boolean hasSpecialCharacter(String str) throws PatternSyntaxException {
        String regEx = "[`~@#$%^&*+=|{}'',\\[\\]<>/~！@#￥%……&*（）——+|{}【】‘]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }


    /**
     * 校验手机
     *
     * @param mobiles
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobileNO(String mobiles) {
        String telRegex = "[1][3546789]\\d{9}";
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    /**
     * 校验密码
     *
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     * 校验邮箱
     *
     * @param mail
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isValidEmail(String mail) {
        return Pattern.matches(REGEX_EMAIL, mail);
    }

    /**
     * 替换手机号中间的4位
     *
     * @param phoneNumber 手机号
     * @param replace     替换后的内容
     * @return
     */
    public static String replacePhone(String phoneNumber, String replace) {
        if (!isEmpty(phoneNumber) && phoneNumber.length() == 11) {
            String start = phoneNumber.substring(0, 3);
            String end = phoneNumber.substring(7);
            return start + replace + end;
        }
        return phoneNumber;
    }

    /**
     * @param pattern 需要格式化的格式,例如"###,###.##" = 123,456.23
     * @param value   需要格式化的值
     * @return 格式后的字符串
     */
    public static String customFormat(String pattern, double value) {
        if (isEmpty(pattern)) {
            return value + "";
        }
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        return output;
    }


    /**
     * @param pattern 需要格式化的格式,例如"###,###.##" = 123,456.23
     * @param value   需要格式化的值
     * @return 格式后的字符串
     */
    public static String customFormat(String pattern, String value) {
        return customFormat(pattern, parseDouble(value));
    }


    /**
     * 将字符串数字转为int类型
     *
     * @param number 需要转换的字符串
     * @return 如果转换失败则返回-1
     */
    public static int parseInt(String number) {
        int i = -1;
        if (!isEmpty(number)) {
            try {
                i = Integer.parseInt(number);
            } catch (NumberFormatException e) {
                Logger.e(e, "字符串转换为 int 异常");
            }
        }

        return i;
    }

    /**
     * 将字符串数字转为float类型
     *
     * @param number 需要转换的字符串
     * @return 如果转换失败则返回-1
     */
    public static float parseFloat(String number) {
        float i = -1;
        if (!isEmpty(number)) {
            try {
                i = Float.parseFloat(number);
            } catch (NumberFormatException e) {
                Logger.e(e, "字符串转换为 float 异常");
            }
        }

        return i;
    }

    /**
     * 将字符串数字转为double类型
     *
     * @param number 需要转换的字符串
     * @return 如果转换失败则返回-1
     */
    public static double parseDouble(String number) {
        double i = -1;
        if (!isEmpty(number)) {
            try {
                i = Double.parseDouble(number);
            } catch (NumberFormatException e) {
                Logger.e(e, "字符串转换为 Double 异常");
            }
        }

        return i;
    }

    /**
     * 驼峰转下划线(简单写法，效率低于)
     */
    public static String humpToLine(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.replaceAll("[A-Z]", "_$0").toLowerCase();
        }
        return str;
    }

    /**
     * @param place   每间隔几位添加空格
     * @param content 需要添加空格的字符串
     * @return 添加空格之后的字符串
     */
    public static String split(int place, String content) {
        if (!TextUtils.isEmpty(content)) {
            content = content.replaceAll(" ", "");

            StringBuilder str = new StringBuilder(content);

            int i = str.length() / place;
            int j = str.length() % place;

            for (int x = (j == 0 ? i - 1 : i); x > 0; x--) {
                str = str.insert(x * place, " ");
            }

            return str.toString();
        }

        return content;
    }

    /**
     * 针对TextView显示中文中出现的排版错乱问题，通过调用此方法得以解决
     *
     * @param str
     * @return 返回全部为全角字符的字符串
     */
    public static String toDBC(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375) {
                c[i] = (char) (c[i] - 65248);
            }

        }
        return new String(c);
    }
}
