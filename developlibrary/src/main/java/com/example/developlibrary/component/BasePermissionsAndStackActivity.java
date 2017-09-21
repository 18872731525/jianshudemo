package com.example.developlibrary.component;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.example.developlibrary.LibLoader;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * 作者：wl on 2017/9/15 10:39
 * 邮箱：wangl@ixinyongjia.com
 */
public class BasePermissionsAndStackActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    private boolean isInitFocus = false;  //记录是否已经初始化获得了焦点，PopupWindow必须在Activity获取焦点后才能显示。
    private boolean isDestroyed = false;    //记录activity是否已经被销毁了
    /*权限相关*/
    public final int BASE_PERMISSION_REQUEST = 100;
    protected String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_SMS,
    };
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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }
}
