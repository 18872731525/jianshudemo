package com.example.dell.jianshudemo.mvp.extend.showofficefile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.dell.jianshudemo.R;
import com.example.dell.jianshudemo.mvp.base.BaseActivity;
import com.example.dell.jianshudemo.mvp.http.DownLoadUtil;
import com.example.developlibrary.utils.BaseFileUtil;
import com.orhanobut.logger.Logger;

import java.io.File;


public class FileDisplayActivity extends BaseActivity {

    private static final String URL = "url";
    private SuperFileView mSuperFileView;
    private String fileUrl;
    private DownLoadUtil downLoadUtil;
    private File dataFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_display);
        initData();
        init();
    }

    private void initData() {
        if (null != getIntent().getStringExtra(URL)) {
            fileUrl = getIntent().getStringExtra(URL);
        } else {
            finish();
        }
    }

    public void init() {
        mSuperFileView = (SuperFileView) findViewById(R.id.mSuperFileView);
        mSuperFileView.setOnGetFilePathListener(new SuperFileView.OnGetFilePathListener() {
            @Override
            public void onGetFilePath(SuperFileView mSuperFileView2) {
                getFilePathAndShowFile(mSuperFileView2);
            }
        });

        Intent intent = this.getIntent();
        String path = (String) intent.getSerializableExtra("path");
        if (!TextUtils.isEmpty(path)) {
            fileUrl = path;
        }
        mSuperFileView.show();
    }


    private void getFilePathAndShowFile(SuperFileView mSuperFileView2) {
        if (fileUrl.contains("http")) {//网络地址要先下载
            showLoadingView();
            downloadPdF();
        } else {
            dataFile = new File(fileUrl);
            mSuperFileView2.displayFile(dataFile);
        }
    }

    private void downloadPdF() {
        Logger.d("下载地址：" + fileUrl);
        String downfileName = "tem.pdf";
        String downloadDir = BaseFileUtil.getDownloadDir();
        dataFile = new File(downloadDir + File.separator + downfileName);
        downLoadUtil = new DownLoadUtil(fileUrl, downfileName, downloadDir, new DownLoadUtil.DonwloadLisener() {
            @Override
            public void loadSuccessd() {
                hideLoadingView();
                hideDefaultView();
                mSuperFileView.displayFile(dataFile);
            }

            @Override
            public void loadFaill() {
                hideLoadingView();
                showErrorDefaultView("文件下载失败");
            }
        });
        downLoadUtil.startDownload();

    }

    public static void show(Context context, String url) {
        Intent intent = new Intent(context, FileDisplayActivity.class);
        intent.putExtra(URL, url);
        context.startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSuperFileView != null) {
            mSuperFileView.onStopDisplay();
        }
    }
}
