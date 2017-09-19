package com.example.dell.jianshudemo.mvp.function;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dell.jianshudemo.R;
import com.example.dell.jianshudemo.TestActivity;
import com.example.developlibrary.component.BaseActivity;
import com.example.developlibrary.utils.UiUtil;
import com.example.developlibrary.view.loading.LoadingView;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getTitleBar().setBgColor(R.color.black).hideLeftTextVeiw();
        getStateBar().setBackgroundColor(R.color.red);

        findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingView();
                UiUtil.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideLoadingView();
                    }
                }, 3000);
            }
        });

        findViewById(R.id.tv_test2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDefaultView();
            }
        });

        findViewById(R.id.tv_test3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(MainActivity.this,TestActivity.class));
            }
        });

        findViewById(R.id.tv_test4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingView(LoadingView.ANIMATION_TYPE_ONE);
                UiUtil.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideLoadingView();
                    }
                }, 3000);
            }
        });
    }

    @Override
    public void onDefaultViewClick(int tag) {
        super.onDefaultViewClick(tag);
        hideDefaultView();

    }
}
