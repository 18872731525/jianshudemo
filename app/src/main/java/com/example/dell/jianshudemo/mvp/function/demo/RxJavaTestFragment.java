package com.example.dell.jianshudemo.mvp.function.demo;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.dell.jianshudemo.R;
import com.example.dell.jianshudemo.mvp.base.BaseFragment;
import com.example.dell.jianshudemo.mvp.function.javabean.IndexMultBean;
import com.example.dell.jianshudemo.mvp.function.javabean.Mail;
import com.example.dell.jianshudemo.mvp.function.javabean.UserInfo;
import com.example.dell.jianshudemo.mvp.http.ApiManager;
import com.example.dell.jianshudemo.mvp.http.bean.TDataBean;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：wl on 2017/9/18 17:07
 * 邮箱：wangl@ixinyongjia.com
 */
public class RxJavaTestFragment extends BaseFragment {


    @Override
    protected View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.demo_fragment_rxjava, null);
        view.findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Observable.create(new ObservableOnSubscribe<List<Mail>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<Mail>> e) throws Exception {
                        try {
                            List<Mail> data = new ArrayList<Mail>();
                            data.add(new Mail());
                            e.onNext(data);
                            e.onComplete();
                        } catch (Exception e1) {
                            e.onError(e1);
                        }
                    }
                }).delay(5000, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<Mail>>() {
                            @Override
                            public void accept(List<Mail> mails) throws Exception {
                                if (mails != null && mails.size() > 0) {
                                    Toast.makeText(getContext(), "RxJava", Toast.LENGTH_LONG).show();
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        });

            }
        });

        view.findViewById(R.id.tv_test2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingView();
                Observable.zip(ApiManager.getIndexData(), ApiManager.login("test", "111"), new BiFunction<TDataBean<IndexMultBean>, TDataBean<UserInfo>, String>() {
                    @Override
                    public String apply(TDataBean<IndexMultBean> indexMultBeanTDataBean, TDataBean<UserInfo> userInfoTDataBean) throws Exception {
                        if (indexMultBeanTDataBean != null && indexMultBeanTDataBean.getData() != null && userInfoTDataBean != null && userInfoTDataBean.getData() != null) {
                            return "Zip结果:" + indexMultBeanTDataBean.getData().getArticles().get(0).getTitle() + "****" + userInfoTDataBean.getCode();
                        } else {
                            return "Zip失败";
                        }
                    }
                }).onErrorReturn(new Function<Throwable, String>() {
                    @Override
                    public String apply(Throwable throwable) throws Exception {
                        hideLoadingView();
                        return "Zip异常" + throwable.getMessage();
                    }
                }).subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        showHint(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoadingView();
                    }

                    @Override
                    public void onComplete() {
                        hideLoadingView();
                    }
                });
            }
        });


        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                emitter = e;
            }
        }).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Logger.d("点击");
                    }
                });
        view.findViewById(R.id.tv_test3).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                emitter.onNext("");
            }

        });


        return view;
    }

    ObservableEmitter<String> emitter = null;

    @Override
    public void onDefaultViewClick(int tag) {
        if (tag == R.mipmap.ic_launcher) {
            hideDefaultView();
            showHint("你点的是刚才设置的自定义的缺省页");
        } else {
            showHint("别的某个缺省页");
        }
    }
}
