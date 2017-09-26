package com.example.dell.jianshudemo.mvp.http;

import com.example.dell.jianshudemo.mvp.function.javabean.IndexMultBean;
import com.example.dell.jianshudemo.mvp.function.javabean.UserInfo;
import com.example.dell.jianshudemo.mvp.http.bean.TDataBean;
import com.example.developlibrary.utils.DeviceUtils;
import com.google.gson.JsonObject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：wl on 2017/9/25 18:02
 * 邮箱：wangl@ixinyongjia.com
 */
public class ApiManager {

    private static <T> Observable<T> subscribeOn(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private static RetrofitApi getJavaApi() {
        return HttpUtil.getInstance().getHttpApi();
    }

    /***************************************业务逻辑接口**************************************/
        /*获取首页数据*/
    public static Observable<TDataBean<IndexMultBean>> getIndexData() {
        Observable<TDataBean<IndexMultBean>> observable = getJavaApi().getIndexData();
        return subscribeOn(observable);
    }

    /*获取首页数据*/
    public static Observable<TDataBean<UserInfo>> login(String username, String pwd) {
        JsonObject data = new JsonObject();
        data.addProperty("mobile", username);
        data.addProperty("password", pwd);
        data.addProperty("device_token", DeviceUtils.getDeviceId());
        Observable<TDataBean<UserInfo>> observable = getJavaApi().login(data);
        return subscribeOn(observable);
    }
}
