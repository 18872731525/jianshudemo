package com.example.developlibrary.view.loading;

import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.example.developlibrary.R;
import com.example.developlibrary.utils.UiUtil;

/**
 * 作者：wl on 2017/9/18 10:29
 * 邮箱：wangl@ixinyongjia.com
 */
public class LoadingView {
    /*动画种类*/
    public static final int ANIMATION_TYPE_DEFULAT = 0;
    public static final int ANIMATION_TYPE_ONE = 1;
    public static final int ANIMATION_TYPE_TWO = 2;
    private FrameLayout rootView;
    private LottieAnimationView iv_anim;

    public LoadingView() {
        initView();
        initLottieAnimation(ANIMATION_TYPE_DEFULAT);
    }

    public LoadingView(int type) {
        initView();
        initLottieAnimation(type);
    }

    private void initView() {
        rootView = (FrameLayout) UiUtil.inflate(R.layout.loading_view);
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        iv_anim = (LottieAnimationView) rootView.findViewById(R.id.iv_anim);
    }

    public View getRootView() {
        return rootView;
    }

    private void initLottieAnimation(int type) {
        iv_anim.setProgress(0f);
        iv_anim.loop(true);
        setAnimaton(type);
        iv_anim.playAnimation();
    }

    public void resetAnimation(int type) {
        iv_anim.cancelAnimation();
        initLottieAnimation(type);
    }

    private void setAnimaton(int type) {
        switch (type) {
            case ANIMATION_TYPE_DEFULAT:
                iv_anim.setAnimation("loading_default.json", LottieAnimationView.CacheStrategy.Strong);
                break;
            case ANIMATION_TYPE_ONE:
                iv_anim.setAnimation("loading_type_one.json", LottieAnimationView.CacheStrategy.Strong);
                break;
            case ANIMATION_TYPE_TWO:
                iv_anim.setAnimation("loading_default.json", LottieAnimationView.CacheStrategy.Strong);
                break;
            default:
                iv_anim.setAnimation("loading_default.json", LottieAnimationView.CacheStrategy.Strong);
                break;
        }
    }
}
