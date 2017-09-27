package com.example.dell.jianshudemo.mvp.extend.showofficefile;

import android.os.Bundle;

import com.example.dell.jianshudemo.R;
import com.example.developlibrary.component.BaseActivity;
import com.tencent.smtt.sdk.WebView;

public class X5WebViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         WebView mWebView = (com.tencent.smtt.sdk.WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);// 支持js
        mWebView.getSettings().setUseWideViewPort(true); //自适应屏幕
        mWebView.loadUrl("http://res.ky-express.com/h5/video/72.html");

    }
}
