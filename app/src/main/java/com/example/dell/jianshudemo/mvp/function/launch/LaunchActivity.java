package com.example.dell.jianshudemo.mvp.function.launch;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.example.dell.jianshudemo.R;
import com.example.dell.jianshudemo.mvp.base.BaseActivity;
import com.example.developlibrary.utils.AnimationUtil;
import com.example.developlibrary.utils.BaseConstant;
import com.example.developlibrary.utils.PreferUtil;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 作者：wl on 2017/9/20 16:35
 * 邮箱：wangl@ixinyongjia.com
 */
public class LaunchActivity extends BaseActivity {
    LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIsLoadTitleBar(false);
        setContentView(R.layout.activity_launch);
        getStateBar().hide();
        requiresMainPermission();
    }

    @AfterPermissionGranted(BASE_PERMISSION_REQUEST)
    private void requiresMainPermission() {
        if (EasyPermissions.hasPermissions(this, permissions[0], permissions[1], permissions[2])) {
            //CacheUtils.getInstance();//获取权限之后再去初始化初存储器!
            //UiUtil.postDelayed(myRunnable, stopTime);
            startLottieLogoAnimation();
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.base_permisson_request), BASE_PERMISSION_REQUEST, permissions[0], permissions[1], permissions[2]);
        }

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, list)) {
            new AppSettingsDialog.Builder(this)
                    .setTitle(R.string.getpermisson_faild)
                    .setPositiveButton(R.string.go_setting)
                    .setRationale(R.string.permisson_tip2)
                    .setNegativeButton("")
                    .build().show();

        } else {
            requiresMainPermission();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            requiresMainPermission();
        }
    }


    private void startLottieLogoAnimation() {
        animationView = (LottieAnimationView) findViewById(R.id.animationView);
        animationView.setAnimation("app_start.json", LottieAnimationView.CacheStrategy.Strong);
        //  animationView.setImageAssetsFolder("lottie");
        animationView.loop(false);
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(AnimationUtil.getAnimationDuraing());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animationView.setProgress(animation.getAnimatedFraction());
                if (animation.getAnimatedFraction() == 1) {
                    //第一次启动应用,跳转到启动页
                    if (!PreferUtil.getBoolean(BaseConstant.FIRST_START, false)) {
                        Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    finish();
                }
            }
        });
        animator.start();
    }
}
