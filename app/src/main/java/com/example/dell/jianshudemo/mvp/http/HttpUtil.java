package com.example.dell.jianshudemo.mvp.http;

import com.example.dell.jianshudemo.mvp.http.ssh.SSHUtil;
import com.example.dell.jianshudemo.mvp.http.ssh.SSLParams;
import com.example.developlibrary.utils.BaseFileUtil;
import com.example.developlibrary.utils.BasePackageUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：wl on 2017/9/21 13:49
 * 邮箱：wangl@ixinyongjia.com
 */
public class HttpUtil {
    //超时时间
    private static final long DEFAULT_READ_TIMEOUT_MILLIS = 20;//提交数据超时时间
    private static final long DEFAULT_WRITE_TIMEOUT_MILLIS = 20;//提交数据超时时间
    private static final long DEFAULT_CONNECT_TIMEOUT_MILLIS = 10;//请求超时时间
    //缓存大小
    private static final long HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024;
    //OkHttpClient
    private OkHttpClient mOkHttpClient;
    //API接口
    private RetrofitApi httpApi;

    private HttpUtil() {
        initOKHttp();
        initRetrofit();
    }

    public static HttpUtil getInstance() {
        return ClassHolder.instace;
    }

    private static class ClassHolder {
        static final HttpUtil instace = new HttpUtil();
    }

    private void initOKHttp() {
        //日志拦截器，专业处理请求过程中的日志 ,header、body数据
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        //根据包的类型控制日志输出与否
        loggingInterceptor.setLevel(BasePackageUtil.isApkDebugable() ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        //SSH相关设置，处理https请求的，默认全部信任，不添加的话遇到https请求可能会出异常，WebView，Picasso，Gilde等框架都会有此问题
        SSLParams sslSocketFactory = SSHUtil.getSslSocketFactory();

        mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.SECONDS)
                //设置连接超时时间
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.SECONDS)
                //设置缓存
                .cache(getDefaultCache())
                //日志拦截器
                .addInterceptor(loggingInterceptor)
                //头部拦截器
                .addInterceptor(new HeadsInterceptor())
                //设置SSH 兼容https
                .sslSocketFactory(sslSocketFactory.sSLSocketFactory, sslSocketFactory.trustManager)
                //默认全部信任
                .hostnameVerifier(SSHUtil.getHostnameVerifier())
                .build();


    }

    private void initRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder()
                //设置网络请求实现
                .client(mOkHttpClient)
                //设置json数据解析实现
                .addConverterFactory(GsonConverterFactory.create())
                //RXJAVA适配器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        //这里主域名的设置单独来弄，万一后台奇葩，一个app里两个主域名的话可以再建一个API_X的接口类，这里类似的单独再配置他的baseurl
        httpApi = builder.baseUrl(Constants.APP_HOST)
                .build()
                .create(RetrofitApi.class);

        //配置了RxJava2CallAdapterFactory即集合RXjava的话httpApi调用xxx方法可以直接返回Observable，然后拿来subscribe
        //不结合RXjava的话返回httpApi调用xxx方法返回Call对象，然后拿来enqueue发请求

        //结合RXjava的优点：更灵活，链式调用，线程切换方便，各种操作符效果吊炸天。 说白了就是可以在处理很复杂嵌套循环请求的时候利用RXJAVA，简化开发
        //不过RXJAVA自身的学习成本的确是个问题，我到现在也是云里雾里，不过既然是框架，，，先用再说啦！

    }

    public OkHttpClient getmOkHttpClient() {
        return mOkHttpClient;
    }

    public RetrofitApi getHttpApi() {
        return httpApi;
    }

    public Cache getDefaultCache() {
        String dir = BaseFileUtil.getHttpCacheDir();
        File file = new File(dir);
        Cache cache = new Cache(file, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);
        return cache;
    }

}
