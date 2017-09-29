package com.example.dell.jianshudemo.mvp.function.launch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dell.jianshudemo.R;
import com.example.dell.jianshudemo.TestActivity;
import com.example.dell.jianshudemo.mvp.extend.showofficefile.FileDisplayActivity;
import com.example.dell.jianshudemo.mvp.function.javabean.IndexMultBean;
import com.example.dell.jianshudemo.mvp.function.javabean.Mail;
import com.example.dell.jianshudemo.mvp.function.javabean.UserInfo;
import com.example.dell.jianshudemo.mvp.http.ApiManager;
import com.example.dell.jianshudemo.mvp.http.bean.TDataBean;
import com.example.developlibrary.component.BaseActivity;
import com.example.developlibrary.utils.UiUtil;
import com.example.developlibrary.utils.jsontool.GsonUtil;
import com.example.developlibrary.view.loading.LoadingView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getTitleBar().setBgColor(R.color.black).hideLeftTextVeiw();
        getStateBar().setBackgroundColor(R.color.red);

        findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "/storage/emulated/0/test.pdf";
                FileDisplayActivity.show(MainActivity.this, url);
            }
        });

        findViewById(R.id.tv_test2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://pre.ixinyongjia.com:447/static/customerFile/audit/201709/S0301505886880%E6%88%B4%E6%9C%9D%E6%98%8E%E5%80%9F%E6%AC%BE%E5%8D%8F%E8%AE%AEZ1@@@20170920164138597.pdf";
                FileDisplayActivity.show(MainActivity.this, url);
            }
        });

        findViewById(R.id.tv_test3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestActivity.class));
            }
        });

        findViewById(R.id.tv_test4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingView(LoadingView.ANIMATION_TYPE_ONE);
                UiUtil.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideLoadingView();
                    }
                }, 3000);
            }
        });

        findViewById(R.id.tv_test5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingView(LoadingView.ANIMATION_TYPE_ONE);
                ApiManager.getIndexData().subscribe(new Observer<TDataBean<IndexMultBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TDataBean<IndexMultBean> value) {
                        Logger.d(GsonUtil.toJson(value));
                        if (value != null && value.getData() != null) {
                            Logger.d("数据" + GsonUtil.toJson(value.getData()));
                        } else {
                            Logger.d("无数据");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        hideLoadingView();
                    }
                });
            }
        });

        findViewById(R.id.tv_test6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Observable<List<Mail>> observable =
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
                }).delay(5000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<Mail>>() {
                            @Override
                            public void accept(List<Mail> mails) throws Exception {
                                if (mails != null && mails.size() > 0) {
                                    Toast.makeText(getApplicationContext(), "RxJava", Toast.LENGTH_LONG).show();
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        });


            }
        });


        findViewById(R.id.tv_test7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Observable<List<Mail>> observable =
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
                }).map(new Function<List<Mail>, Mail>() {
                    @Override
                    public Mail apply(List<Mail> mails) throws Exception {
                        if (mails != null) {
                            return mails.get(0);
                        } else {
                            return null;
                        }
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Mail>() {
                            @Override
                            public void accept(Mail mails) throws Exception {
                                if (mails != null) {
                                    Toast.makeText(getApplicationContext(), "RxJava", Toast.LENGTH_LONG).show();
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        });
            }
        });

        findViewById(R.id.tv_test8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingView();
                ApiManager.login("18872731525", "654321").flatMap(new Function<TDataBean<UserInfo>, ObservableSource<TDataBean<IndexMultBean>>>() {
                    @Override
                    public ObservableSource<TDataBean<IndexMultBean>> apply(TDataBean<UserInfo> userInfoTDataBean) throws Exception {
                        if (userInfoTDataBean != null && userInfoTDataBean.getData() != null) {
                            Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                            return ApiManager.getIndexData();
                        } else {
                            Toast.makeText(getApplicationContext(), "登录失败:" + userInfoTDataBean.getError_msg(), Toast.LENGTH_SHORT).show();
                            hideLoadingView();
                            return null;

                        }

                    }
                }).onErrorReturn(new Function<Throwable, TDataBean<IndexMultBean>>() {
                    @Override
                    public TDataBean<IndexMultBean> apply(Throwable throwable) throws Exception {
                        Toast.makeText(getBaseContext(), "异常：" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        hideLoadingView();
                        return null;
                    }
                }).delay(2000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).map(new Function<TDataBean<IndexMultBean>, String>() {
                    @Override
                    public String apply(TDataBean<IndexMultBean> indexMultBeanTDataBean) throws Exception {
                        if (indexMultBeanTDataBean != null && indexMultBeanTDataBean.getData() != null) {
                            return indexMultBeanTDataBean.getData().getArticles().get(0).getTitle();
                        } else {
                            return null;
                        }

                    }
                }).subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        Toast.makeText(getBaseContext(), "第一天，我存在，第一次会回想起来：" + value, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getBaseContext(), "错误2：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                        hideLoadingView();
                    }
                });
            }
        });


        findViewById(R.id.tv_test9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingView();
                Observable.zip(ApiManager.getIndexData(), ApiManager.login("18872731525", "654321"), new BiFunction<TDataBean<IndexMultBean>, TDataBean<UserInfo>, String>() {
                    @Override
                    public String apply(TDataBean<IndexMultBean> indexMultBeanTDataBean, TDataBean<UserInfo> userInfoTDataBean) throws Exception {
                        if (indexMultBeanTDataBean != null && indexMultBeanTDataBean.getData() != null && userInfoTDataBean != null && userInfoTDataBean.getData() != null) {
                            return "Zip结果:" + indexMultBeanTDataBean.getData().getArticles().get(0).getTitle() + userInfoTDataBean.getData().getMobile();
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

                    }

                    @Override
                    public void onComplete() {
                        hideLoadingView();
                    }
                });
            }
        });

        findViewById(R.id.tv_test10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        for (int i = 0; i < 100; i++) {
                            e.onNext(new Random().nextInt(100));
                        }
                        e.onComplete();
                    }
                }).filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {

                        if (integer > 50 && integer % 3 == 0) {
                            return true;
                        } else {
                            return false;
                        }

                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).sample(5, TimeUnit.MICROSECONDS).subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {

                        showHint(value + "");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }
        });

        findViewById(R.id.tv_test12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        e.onNext("点击");
                    }
                }).throttleFirst(1, TimeUnit.SECONDS,AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Logger.d("点击");
                    }
                });
            }
        });

        findViewById(R.id.tv_test11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mail mali1 = new Mail();
                mali1.setTitle("送你离开");

                Mail mali12 = new Mail();
                mali12.setTitle("千里之外");

                Mail mali13 = new Mail();
                mali13.setTitle("牡丹江");

                final List<Mail> datas = new ArrayList<Mail>();
                datas.add(mali1);
                datas.add(mali12);
                datas.add(mali13);


                Observable.intervalRange(0, datas.size(), 1, 5, TimeUnit.SECONDS).map(new Function<Long, Mail>() {
                    @Override
                    public Mail apply(Long aLong) throws Exception {
                        int i = aLong.intValue();
                        return datas.get(i);
                    }
                }).throttleFirst(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Mail>() {
                    @Override
                    public void accept(Mail mail) throws Exception {
                        Toast.makeText(getApplicationContext(), mail.getTitle(), Toast.LENGTH_LONG).show();
                    }
                });


//                Observable.fromIterable(datas).filter(new Predicate<Mail>() {
//                    @Override
//                    public boolean test(Mail mail) throws Exception {
//                        if (mail.getTitle().contains("外")) {
//                            return false;
//                        } else {
//                            return true;
//                        }
//                    }
//                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                        .timer(1000, TimeUnit.SECONDS, Schedulers.newThread()).subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Long value) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

            }
        });

    }

    @Override
    public void onDefaultViewClick(int tag) {
        super.onDefaultViewClick(tag);
        hideDefaultView();

    }
}
