package com.example.dell.jianshudemo.mvp.http;

import com.example.dell.jianshudemo.mvp.function.javabean.IndexMultBean;
import com.example.dell.jianshudemo.mvp.function.javabean.UserInfo;
import com.example.dell.jianshudemo.mvp.http.bean.TDataBean;
import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 作者：wl on 2017/9/21 13:50
 * 邮箱：wangl@ixinyongjia.com
 */
public interface RetrofitApi {
    /*获取首页数据*/
    @POST(Constants.FUNCTION_INDEX)
    Observable<TDataBean<IndexMultBean>> getIndexData();

    /*登录*/
    @POST(Constants.FUNCTION_LOGIN)
    Observable<TDataBean<UserInfo>> login(@Body JsonObject object);
}
