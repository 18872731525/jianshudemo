package com.example.dell.jianshudemo.mvp.http.bean;

/**
 * 作者：wl on 2017/9/21 15:29
 * 邮箱：wangl@ixinyongjia.com
 */
public interface IBaseResult {
    boolean isSucceed();

    String getError_msg();

    String getError_code();

}
