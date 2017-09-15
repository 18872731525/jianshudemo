package com.example.developlibrary.view.defaultview;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.developlibrary.R;
import com.example.developlibrary.interfaces.OnClickDefaultBtn;
import com.example.developlibrary.utils.UiUtil;

/**
 * 作者：wl on 2017/9/15 17:18
 * 邮箱：wangl@ixinyongjia.com
 */
public class DefaultView {

    /*缺省页顶层布局*/
    private LinearLayout defaultPageView;
    /*缺省图标*/
    private ImageView ivDefaultLcon;
    /*缺省文本*/
    private TextView tvHint;
    /*缺省按钮*/
    private TextView tvLoad;
    /*按钮点击回调*/
    private OnClickDefaultBtn callback;
    /*属性构造器Build*/
    private DefaultViewBuild build;
    /*点击时的缺省图片id-识别点击效果*/
    private int tag;

    public DefaultView() {
        init();
    }

    private void init() {
        /*初始化构造器*/
        build = new DefaultViewBuild();
             /*初始化View*/
        defaultPageView = (LinearLayout) UiUtil.inflate(R.layout.default_view);
        tvLoad = (TextView) defaultPageView.findViewById(R.id.tv_load);
        tvHint = (TextView) defaultPageView.findViewById(R.id.tv_hint);
        ivDefaultLcon = (ImageView) defaultPageView.findViewById(R.id.iv_default_lcon);
        tvLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onDefaultViewClick(tag);
                }
            }
        });
    }

    public void show() {
        if (defaultPageView.getVisibility() != View.VISIBLE) {
            defaultPageView.setVisibility(View.VISIBLE);
        }
    }

//    private void reset() {
//        build.setIcon(R.mipmap.defaultpage_nonetwork_icon)
//                .setHintText(R.string.seem_not_network)
//                .setBtnText(R.string.again_load);
//    }

    public void hide() {
        if (defaultPageView.getVisibility() != View.GONE) {
            defaultPageView.setVisibility(View.GONE);
        }
    }

    public void setClickListener(OnClickDefaultBtn listener) {
        callback = listener;
    }

    /*Build模式,设置私有属性，并返回Build自身，实现链式调用*/
    public class DefaultViewBuild {

        /*设置缺省图片，同时更新tag，点击事件可以根据tag处理不同类型的事件*/
        public DefaultViewBuild setIcon(@DrawableRes int resId) {
            tag = resId;
            ivDefaultLcon.setImageResource(resId);
            return this;
        }

        /*设置缺省文本*/
        public DefaultViewBuild setHintText(String text) {
            tvHint.setText(text);
            return this;
        }

        public DefaultViewBuild setHintText(@StringRes int resId) {
            tvHint.setText(resId);
            return this;
        }

        /*设置缺省按钮文本*/
        public DefaultViewBuild setBtnText(String text) {
            tvLoad.setText(text);
            return this;
        }

        public DefaultViewBuild setBtnText(@StringRes int resId) {
            tvLoad.setText(resId);
            return this;
        }

        /*设置缺省按钮是否可见*/
        public DefaultViewBuild setBtnVisible(int visibility) {
            tvLoad.setVisibility(visibility);
            return this;
        }

        public void show() {
            if (defaultPageView.getVisibility() != View.VISIBLE) {
                defaultPageView.setVisibility(View.VISIBLE);
            }
        }
    }
}
