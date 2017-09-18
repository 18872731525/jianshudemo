package com.example.dell.jianshudemo;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.developlibrary.component.BaseActivity;
import com.example.developlibrary.component.BaseFragment;

/**
 * 作者：wl on 2017/9/18 17:04
 * 邮箱：wangl@ixinyongjia.com
 */
public class TestActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(cretaeView());
        init();
    }

    private void init() {
        getTitleBar().setTitle("测试");
        BaseFragment fragment = new TestFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.id_fragment, fragment).commitAllowingStateLoss();
    }

    private View cretaeView() {
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setId(R.id.id_fragment);
        return frameLayout;
    }
}
