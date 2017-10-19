package com.example.dell.jianshudemo.mvp.function.member.login;

import com.example.dell.jianshudemo.mvp.base.BasePresenterImpl;
import com.example.dell.jianshudemo.mvp.function.javabean.UserInfo;
import com.example.dell.jianshudemo.mvp.http.ApiManager;
import com.example.dell.jianshudemo.mvp.http.HttpObserver;
import com.example.dell.jianshudemo.mvp.http.bean.TDataBean;

/**
 * description:
 * create people: dell
 * create time: 2017/09/19 17:11
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {


    @Override
    public void login(String acount, String pwd) {
        ApiManager.login(acount, pwd).subscribe(new HttpObserver<TDataBean<UserInfo>>(mView) {
            @Override
            public void onSucceed(TDataBean<UserInfo> value) {
                mView.loginSucceed();

            }

            @Override
            public void onDefeat(TDataBean<UserInfo> value) {
                mView.hideLoadingView();
                mView.loginFaild();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }
}
