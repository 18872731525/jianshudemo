package com.example.dell.jianshudemo.mvp.http;

import android.content.Intent;

import com.example.dell.jianshudemo.mvp.http.bean.TDataBean;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * 作者：wl on 2017/9/21 13:50
 * 邮箱：wangl@ixinyongjia.com
 */
public interface Api {
    /*获取首页数据*/
    @POST(Constants.FUNCTION_INDEX)
    Observable<TDataBean<Intent>> getIndexListData();

}
