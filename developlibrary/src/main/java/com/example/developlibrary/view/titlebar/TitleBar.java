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
import com.example.developlibrary.utils.UiUtil;

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
        return this;
    }

    @Override
    public BaseTitleBar setBgColor(int color) {
        rly_root.setBackgroundColor(UiUtil.getColor(color));
        return this;
    }

    @Override
    public BaseTitleBar setTitle(@StringRes int StringResId) {
        tvTitle.setText(StringResId);
        return this;
    }

    @Override
    public BaseTitleBar setTitle(String text) {
        tvTitle.setText(text);
        return this;
    }

    @Override
    public BaseTitleBar setTitleIcon(@DrawableRes int drawableId) {
        tvTitle.setCompoundDrawables(null, null, UiUtil.getDrawable(drawableId), null);
        return this;
    }

    @Override
    public BaseTitleBar setTitleTextColor(int color) {
        tvTitle.setTextColor(color);
        return this;
    }

    @Override
    public BaseTitleBar setRightText(String text) {
        tvRight.setText(text);
        return this;
    }

    @Override
    public BaseTitleBar setRightText(@StringRes int stringResId) {
        tvRight.setText(stringResId);
        return this;
    }

    @Override
    public BaseTitleBar setRightIcon(@DrawableRes int drawableId) {
        tvRight.setCompoundDrawables(null, null, UiUtil.getDrawable(drawableId), null);
        return this;
    }

    @Override
    public BaseTitleBar showRightTextView() {
        if (tvRight.getVisibility() != VISIBLE) {
            tvRight.setVisibility(VISIBLE);
        }
        return this;
    }

    @Override
    public BaseTitleBar hideRightTextView() {
        if (tvRight.getVisibility() != GONE) {
            tvRight.setVisibility(GONE);
        }
        return this;
    }

    @Override
    public BaseTitleBar setRightTextColor(int color) {
        tvRight.setTextColor(color);
        return this;
    }

    @Override
    public BaseTitleBar setRightTextClickListener(OnClickListener listener) {
        tvRight.setOnClickListener(listener);
        return this;
    }

    @Override
    public BaseTitleBar setLeftText(String text) {
        tvLeft.setText(text);
        return this;
    }

    @Override
    public BaseTitleBar setLeftText(@StringRes int stringResId) {
        tvLeft.setText(stringResId);
        return this;
    }

    @Override
    public BaseTitleBar setLeftIcon(@DrawableRes int drawableId) {
        tvLeft.setCompoundDrawables(UiUtil.getDrawable(drawableId), null, null, null);
        return this;
    }

    @Override
    public BaseTitleBar showLeftTextView() {
        if (tvLeft.getVisibility() != VISIBLE) {
            tvLeft.setVisibility(VISIBLE);
        }
        return this;
    }

    @Override
    public BaseTitleBar hideLeftTextVeiw() {
        if (tvLeft.getVisibility() != GONE) {
            tvLeft.setVisibility(GONE);
        }
        return this;
    }

    @Override
    public BaseTitleBar setLeftTextColor(int color) {
        tvLeft.setTextColor(color);
        return this;
    }

    @Override
    public BaseTitleBar setLeftTextClickListener(OnClickListener listener) {
        tvLeft.setOnClickListener(listener);
        return this;
    }
}
