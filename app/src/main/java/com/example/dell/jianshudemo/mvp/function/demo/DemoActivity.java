package com.example.dell.jianshudemo.mvp.function.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.dell.jianshudemo.R;
import com.example.dell.jianshudemo.mvp.base.BaseActivity;
import com.example.dell.jianshudemo.mvp.base.BaseFragment;


public class DemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(cretaeView());
        init();
    }

    private void init() {
        getTitleBar().setTitle("测试");
        BaseFragment fragment = getFragmentByIntent();
        getSupportFragmentManager().beginTransaction().replace(R.id.id_fragment, fragment).commitAllowingStateLoss();
    }

    private View cretaeView() {
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setId(R.id.id_fragment);
        return frameLayout;
    }

    private BaseFragment getFragmentByIntent() {
        String taget = getIntent().getStringExtra("data");
        switch (taget) {
            case "Rxjava":
                return new RxJavaTestFragment();
            case "http":
                return new HttpDemoFragment();
            case "UIDemo":
                return new UITestFragment();
            default:
                return new UITestFragment();

        }
    }

    public static void starSelf(Context context, String dataString) {
        Intent intent = new Intent(context, DemoActivity.class);
        intent.putExtra("data", dataString);
        context.startActivity(intent);
    }
}
