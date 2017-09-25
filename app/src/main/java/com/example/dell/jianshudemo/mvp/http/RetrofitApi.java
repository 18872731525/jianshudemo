package com.example.dell.jianshudemo.mvp.http;

import android.content.Intent;

import com.example.dell.jianshudemo.mvp.http.bean.TDataBean;

import java.util.HashMap;

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


    public static Observable<TBaseResult<XinYanBankTaskResult>> createOperatorTask(String
                                                                                           phone, String pwd) {
        UserInfoManager instance = UserInfoManager.getInstance();
        HashMap<String, String> userCodeMap = new HashMap<>();
        userCodeMap.put("customerId", instance.getUserCode());
        userCodeMap.put("account", phone);
        userCodeMap.put("password", pwd);
        userCodeMap.put("realName", instance.getUserInfo().getCust_name());
        userCodeMap.put("idCardNo", instance.getUserInfo().getCust_idcard());
        Observable<TBaseResult<XinYanBankTaskResult>> observable = getJavaApi().createOperatorTask(userCodeMap);
        return subscribeOn(observable);
    }

}
