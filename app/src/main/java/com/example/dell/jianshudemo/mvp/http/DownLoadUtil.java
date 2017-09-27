package com.example.dell.jianshudemo.mvp.http;

import com.example.developlibrary.utils.UiUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.developlibrary.utils.UiUtil.post;

/**
 * 作者：wl on 2017/4/13 09:39
 * 邮箱：wangl@ixinyongjia.com
 */
public class DownLoadUtil {
    private String downloadUrl;
    private String fileName;
    private String filePath;
    private DonwloadLisener lisener;
    private Thread downloadThread;

    public DownLoadUtil(String targetUrl, String fName, String fPath, DonwloadLisener callback) {
        downloadUrl = targetUrl;
        fileName = fName;
        filePath = fPath;
        lisener = callback;
        initDownLoadThread();
    }

    private void initDownLoadThread() {
        downloadThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder().url(downloadUrl).build();
                HttpUtil.getInstance().getmOkHttpClient().newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        // 下载失败
                        post(new Runnable() {
                            @Override
                            public void run() {
                                lisener.loadFaill();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        try {
                            //得到输入流
                            InputStream inputStream = response.body().byteStream();
                            //获取自己数组
                            byte[] getData = readInputStream(inputStream);

                            //文件保存位置
                            File saveDir = new File(filePath);
                            if (!saveDir.exists()) {
                                saveDir.mkdir();
                            }
                            File file = new File(saveDir + File.separator + fileName);
                            FileOutputStream fos = new FileOutputStream(file);
                            fos.write(getData);
                            if (fos != null) {
                                fos.close();
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (lisener != null) {
                                UiUtil.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        lisener.loadSuccessd();
                                    }
                                });
                            }
                        } catch (Exception e) {
                            UiUtil.post(new Runnable() {
                                @Override
                                public void run() {
                                    lisener.loadFaill();
                                }
                            });
                        } finally {

                        }
                    }
                });
            }
        });

    }

    public void startDownload() {
        if (downloadThread != null && !downloadThread.isAlive()) {
            downloadThread.start();
        } else {
            initDownLoadThread();
            downloadThread.start();
        }
    }

    public void stopDownLoad() {
        if (downloadThread != null && downloadThread.isAlive()) {
            downloadThread.interrupt();
        }
    }


    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public byte[] readInputStream(InputStream inputStream) {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while ((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
            if (lisener != null) {
                post(new Runnable() {
                    @Override
                    public void run() {
                        lisener.loadFaill();
                    }
                });
            }
        }

        return bos.toByteArray();
    }

    public interface DonwloadLisener {
        void loadSuccessd();

        void loadFaill();
    }


}
