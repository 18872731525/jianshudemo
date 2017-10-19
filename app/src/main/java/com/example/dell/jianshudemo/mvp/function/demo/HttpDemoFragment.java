package com.example.dell.jianshudemo.mvp.function.demo;

import android.view.LayoutInflater;
import android.view.View;

import com.example.dell.jianshudemo.R;
import com.example.dell.jianshudemo.mvp.base.BaseFragment;
import com.example.dell.jianshudemo.mvp.function.javabean.IndexMultBean;
import com.example.dell.jianshudemo.mvp.http.ApiManager;
import com.example.dell.jianshudemo.mvp.http.HttpObserver;
import com.example.dell.jianshudemo.mvp.http.bean.TDataBean;

/**
 * 作者：wl on 2017/9/18 17:07
 * 邮箱：wangl@ixinyongjia.com
 */
public class HttpDemoFragment extends BaseFragment {


    @Override
    protected View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.demo_fragment_http, null);

        view.findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingView();
                ApiManager.getIndexData().subscribe(new HttpObserver<TDataBean<IndexMultBean>>(HttpDemoFragment.this) {
                    @Override
                    public void onSucceed(TDataBean<IndexMultBean> value) {
                        if (value.getData() != null) {
                            hideLoadingView();
                            showHint("请求成功");
                        }

                    }

                    @Override
                    public void onDefeat(TDataBean<IndexMultBean> value) {
                        super.onDefeat(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
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
