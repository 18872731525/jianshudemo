package com.example.dell.jianshudemo.mvp.http.bean;

/**
 * 作者：wl on 2017/9/21 15:33
 * 邮箱：wangl@ixinyongjia.com
 */
public class TDataBean<T> extends BaseResult {
    private T Data;

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
