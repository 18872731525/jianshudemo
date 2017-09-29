package com.example.dell.jianshudemo.mvp.http;

import com.example.dell.jianshudemo.R;
import com.example.dell.jianshudemo.mvp.base.BaseView;
import com.example.dell.jianshudemo.mvp.http.bean.BaseResult;
import com.example.developlibrary.utils.NetworkUtil;
import com.example.developlibrary.utils.UiUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 作者：wl on 2017/9/28 11:00
 * 邮箱：wangl@ixinyongjia.com
 */
public abstract class HttpObserver<T extends BaseResult> implements Observer<T> {
    private Disposable disposable;
    private BaseView baseView;

    public HttpObserver(BaseView view) {
        baseView = view;
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.disposable = d;
        baseView.addDisposable(d);
    }

    @Override
    public void onNext(T value) {
        baseView.hideLoadingView();
        if (value.isSucceed()) {
            onSucceed(value);
        } else {
            onDefeat(value);
        }
        baseView.removeDisposable(disposable);
    }

    /**
     * 网络请求异常的回调
     */
    @Override
    public void onError(Throwable e) {
        baseView.removeDisposable(disposable);
        baseView.hideLoadingView();
        if (NetworkUtil.isNetworkerConnect()) {
            baseView.showHint(UiUtil.getString(R.string.unknow_error));
        } else {
            baseView.showHint(UiUtil.getString(R.string.network_error));
        }

    }

    @Override
    public void onComplete() {

    }

    /**
     * 网络请求成功，业务处理成功的回调
     */
    public abstract void onSucceed(T value);

    /**
     * 网络请求成功，业务处理失败的回调
     */
    public void onDefeat(T value) {
        baseView.showHint(value.getError_msg());
    }


}
