package com.example.dell.jianshudemo.mvp.function.launch;

import android.os.Bundle;
import android.view.View;

import com.example.dell.jianshudemo.R;
import com.example.dell.jianshudemo.mvp.base.BaseActivity;
import com.example.dell.jianshudemo.mvp.function.demo.DemoActivity;
import com.example.dell.jianshudemo.mvp.function.member.login.LoginActivity;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getTitleBar().setBgColor(R.color.black).hideLeftTextVeiw();
        getStateBar().setBackgroundColor(R.color.red);


        //基本UI DEMO
        findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemoActivity.starSelf(getBaseContext(), "UIDemo");

            }
        });

        //activity中直接网络请求
        findViewById(R.id.tv_test2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemoActivity.starSelf(getBaseContext(), "http");
            }
        });

        //MVP模式写法示例
        findViewById(R.id.tv_test3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.starSelf(getBaseContext(), null);
            }
        });

        findViewById(R.id.tv_test4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemoActivity.starSelf(getBaseContext(), "Rxjava");
            }
        });





//
//        findViewById(R.id.tv_test10).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Observable.create(new ObservableOnSubscribe<Integer>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                        for (int i = 0; i < 100; i++) {
//                            e.onNext(new Random().nextInt(100));
//                        }
//                        e.onComplete();
//                    }
//                }).filter(new Predicate<Integer>() {
//                    @Override
//                    public boolean demo_fragment(Integer integer) throws Exception {
//
//                        if (integer > 50 && integer % 3 == 0) {
//                            return true;
//                        } else {
//                            return false;
//                        }
//
//                    }
//                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).sample(5, TimeUnit.MICROSECONDS).subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer value) {
//
//                        showHint(value + "");
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
//            }
//        });
//
//        findViewById(R.id.tv_test12).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                Observable.create(new ObservableOnSubscribe<String>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<String> e) throws Exception {
//                        e.onNext("点击");
//                    }
//                }).throttleFirst(1, TimeUnit.SECONDS,AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        Logger.d("点击");
//                    }
//                });
//            }
//        });

//        findViewById(R.id.tv_test11).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Mail mali1 = new Mail();
//                mali1.setTitle("送你离开");
//
//                Mail mali12 = new Mail();
//                mali12.setTitle("千里之外");
//
//                Mail mali13 = new Mail();
//                mali13.setTitle("牡丹江");
//
//                final List<Mail> datas = new ArrayList<Mail>();
//                datas.add(mali1);
//                datas.add(mali12);
//                datas.add(mali13);
//
//
//                Observable.intervalRange(0, datas.size(), 1, 5, TimeUnit.SECONDS).map(new Function<Long, Mail>() {
//                    @Override
//                    public Mail apply(Long aLong) throws Exception {
//                        int i = aLong.intValue();
//                        return datas.get(i);
//                    }
//                }).throttleFirst(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Mail>() {
//                    @Override
//                    public void accept(Mail mail) throws Exception {
//                        Toast.makeText(getApplicationContext(), mail.getTitle(), Toast.LENGTH_LONG).show();
//                    }
//                });
//
//            }
//        });

    }

    @Override
    public void onDefaultViewClick(int tag) {
        super.onDefaultViewClick(tag);
        hideDefaultView();

    }
}
