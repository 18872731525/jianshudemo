package com.example.dell.jianshudemo.mvp.function.demo;

import android.view.LayoutInflater;
import android.view.View;

import com.example.dell.jianshudemo.R;
import com.example.dell.jianshudemo.mvp.base.BaseFragment;
import com.example.developlibrary.utils.UiUtil;

/**
 * 作者：wl on 2017/9/18 17:07
 * 邮箱：wangl@ixinyongjia.com
 */
public class UITestFragment extends BaseFragment {


    @Override
    protected View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.demo_fragment, null);
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
                getStateBar().setBackgroundColor(R.color.red);
            }
        });

        view.findViewById(R.id.tv_test3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTitleBar().setBgColor(R.color.red)
                        .setRightText("右侧")
                        .setLeftText("左侧").setLeftIcon(R.mipmap.back_ico)
                        .setTitle("标题");


            }
        });

        view.findViewById(R.id.tv_test4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               showErrorDefaultView("");
//                showNoDataDefaultView("");
                //showDefaultView();
                getDefaultViewBuild().setBtnText("自定义").setIcon(R.mipmap.ic_launcher).show();
            }
        });
        return view;
    }

    @Override
    public void onDefaultViewClick(int tag) {
        if (tag == R.mipmap.ic_launcher) {
            hideDefaultView();
            showHint("你点的是刚才设置的自定义的缺省页");
        } else {
            showHint("别的某个缺省页");
        }
    }
}
