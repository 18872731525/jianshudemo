package com.example.developlibrary.component;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.developlibrary.LibLoader;

/**
 * 作者：wl on 2017/9/15 10:39
 * 邮箱：wangl@ixinyongjia.com
 */
public class BaseStackActivity extends AppCompatActivity {
    private boolean isInitFocus = false;  //记录是否已经初始化获得了焦点，PopupWindow必须在Activity获取焦点后才能显示。
    private boolean isDestroyed = false;    //记录activity是否已经被销毁了

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LibLoader.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LibLoader.removeActivity(this);
        isDestroyed = true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            isInitFocus = true;
        }
    }

    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }

    public boolean isInitFocus() {
        return isInitFocus;
    }
}
