package com.example.dell.jianshudemo;

import android.view.LayoutInflater;
import android.view.View;

import com.example.developlibrary.component.BaseFragment;
import com.example.developlibrary.utils.UiUtil;

/**
 * 作者：wl on 2017/9/18 17:07
 * 邮箱：wangl@ixinyongjia.com
 */
public class TestFragment extends BaseFragment {


    @Override
    protected View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.test, null);
        view.findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
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

        view.findViewById(R.id.tv_test2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDefaultView();
            }
        });
//
//        view.findViewById(R.id.tv_test3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showErrorDefaultView("");
//            }
//        });
//
//        view.findViewById(R.id.tv_test4).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showLoadingView(LoadingView.ANIMATION_TYPE_ONE);
//                UiUtil.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        hideLoadingView();
//                    }
//                }, 3000);
//            }
//        });
        return view;
    }


}
