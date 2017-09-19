package com.example.dell.jianshudemo.mvp.function.member.login;


import com.example.dell.jianshudemo.mvp.base.MVPBaseActivity;
import android.os.Bundle;

import com.example.dell.jianshudemo.R;

/**
 *  description:
 *  create people: dell
 *  create time: 2017/09/19 17:11
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

    }
}
