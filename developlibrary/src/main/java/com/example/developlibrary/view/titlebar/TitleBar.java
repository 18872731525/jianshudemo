package com.example.developlibrary.view.titlebar;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.developlibrary.R;

/**
 * 作者：wl on 2017/9/15 13:31
 * 邮箱：wangl@ixinyongjia.com
 */
public class TitleBar extends FrameLayout implements BaseTitleBar {
    private RelativeLayout rly_root;
    private TextView tvLeft;
    private TextView tvTitle;
    private TextView tvRight;

    public TitleBar(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.title_bar_view, this);
        rly_root = (RelativeLayout) findViewById(R.id.rly_root);
        tvLeft = (TextView) findViewById(R.id.tv_left);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvRight = (TextView) findViewById(R.id.tv_right);

        int id = getId();
        if (id < 1) {
            setId(R.id.id_title_bar);
        }
    }


    @Override
    public View getView() {
        return null;
    }

    @Override
    public BaseTitleBar setBgColor(int color) {
        return null;
    }

    @Override
    public BaseTitleBar setTitle(@StringRes int StringResId) {
        return null;
    }

    @Override
    public BaseTitleBar setTitle(String text) {
        return null;
    }

    @Override
    public BaseTitleBar setTitleIcon(@DrawableRes int drawableId) {
        return null;
    }

    @Override
    public BaseTitleBar setTitleTextColor(int color) {
        return null;
    }

    @Override
    public BaseTitleBar setRightText(String text) {
        return null;
    }

    @Override
    public BaseTitleBar setRightText(@StringRes int stringResId) {
        return null;
    }

    @Override
    public BaseTitleBar setRightIcon(@DrawableRes int drawableId) {
        return null;
    }

    @Override
    public BaseTitleBar showRightTextView() {
        return null;
    }

    @Override
    public BaseTitleBar hideRightTextView() {
        return null;
    }

    @Override
    public BaseTitleBar setRightTextColor(int color) {
        return null;
    }

    @Override
    public BaseTitleBar setRightTextClickListener(OnClickListener listener) {
        return null;
    }

    @Override
    public BaseTitleBar setLeftText(String text) {
        return null;
    }

    @Override
    public BaseTitleBar setLeftText(@StringRes int stringResId) {
        return null;
    }

    @Override
    public BaseTitleBar setLeftIcon(@DrawableRes int drawableId) {
        return null;
    }

    @Override
    public BaseTitleBar showLeftTextView() {
        return null;
    }

    @Override
    public BaseTitleBar hideLeftTextVeiw() {
        return null;
    }

    @Override
    public BaseTitleBar setLeftTextColor(int color) {
        return null;
    }

    @Override
    public BaseTitleBar setLeftTextClickListener(OnClickListener listener) {
        return null;
    }
}
