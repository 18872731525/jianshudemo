package com.example.dell.jianshudemo.mvp.http;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 作者：wl on 2017/9/21 14:47
 * 邮箱：wangl@ixinyongjia.com
 */
public class HeadsInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        builder.header("head_data", "this is test data which add from HeadsInterceptor");
        request = builder.build();

        Response response = chain.proceed(request);
        handleResponseData(response, request);
        return response;

    }

    private void handleResponseData(Response response, Request request) throws IOException {
        if (response == null) {
            return;
        }
        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            return;
        }
        long contentLength = responseBody.contentLength();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            charset = contentType.charset(UTF8);
        }

        if (contentLength != 0) {
            String dataString = buffer.clone().readString(charset);
            //这里可以对请求结果捕获，并加以处理

//            if (!TextUtils.isEmpty(dataString) && dataString.contains("未知错误") && dataString.contains("4000")) {
//                Throwable error = new Throwable("4000未知错误");
//                 /*输入*/
//                setInputData(request);
//                CrashReport.putUserData(AppLoader.getInstance(), "userinfo", UserInfoManager.getInstance().getMainDataString());
//                CrashReport.postCatchedException(error);
//            }
//            if (!TextUtils.isEmpty(dataString) && dataString.contains("非法请求") && dataString.contains("6000")) {
//                Throwable error = new Throwable("6000非法请求");
//                  /*输入*/
//                setInputData(request);
//                CrashReport.postCatchedException(error);
//                CrashReport.putUserData(AppLoader.getInstance(), "userinfo", UserInfoManager.getInstance().getMainDataString());
//            }

        }
    }

}
