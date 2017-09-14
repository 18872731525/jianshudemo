package com.example.developlibrary.utils.jsontool;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 作者：wl on 2017/9/14 14:40
 * 邮箱：wangl@ixinyongjia.com
 */
public class GsonUtil {
    private static Gson mGson = new Gson();

    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        return mGson.fromJson(jsonString, clazz);
    }


    public static <T> List<T> fromJsonList(String jsonString, Class<T> clazz) {
        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
        return mGson.fromJson(jsonString, listType);
    }

    public static String toJson(Object obj) {
        return mGson.toJson(obj);
    }

}
