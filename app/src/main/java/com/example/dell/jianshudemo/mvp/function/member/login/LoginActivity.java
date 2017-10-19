package com.example.dell.jianshudemo.mvp.function.member.login;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dell.jianshudemo.R;
import com.example.dell.jianshudemo.mvp.base.MVPBaseActivity;

/**
 * description:
 * create people: dell
 * create time: 2017/09/19 17:11
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {
    EditText et_account;
    EditText et_pwd;
    TextView tv_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();

    }

    private void initview() {
        et_account = (EditText) findViewById(R.id.et_acount);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        tv_submit = (TextView) findViewById(R.id.tv_submit);
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitData();
            }
        });

    }

    private void submitData() {
        if (checkData() && isNetworkerConnectHint()) {
            String account = et_account.getText().toString().trim();
            String pwd = et_pwd.getText().toString().trim();
            showLoadingView();
            mPresenter.login(account, pwd);
        }

    }

    private boolean checkData() {
        if (TextUtils.isEmpty(et_account.getText().toString().trim())) {
            return false;
        } else if (TextUtils.isEmpty(et_pwd.getText().toString().trim())) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void loginSucceed() {
        showHint("请求成功，数据可能不对应哦");
    }

    @Override
    public void loginFaild() {

    }


    public static void starSelf(Context context, String dataString) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("data", dataString);
        context.startActivity(intent);
    }
}
