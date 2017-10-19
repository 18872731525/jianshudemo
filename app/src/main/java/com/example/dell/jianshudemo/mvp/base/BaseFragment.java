package com.example.dell.jianshudemo.mvp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import com.example.developlibrary.interfaces.OnClickDefaultBtn;
import com.example.developlibrary.utils.NetworkUtil;
import com.example.developlibrary.view.defaultview.DefaultView;
import com.example.developlibrary.view.statebar.BaseStateBar;
import com.example.developlibrary.view.titlebar.BaseTitleBar;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 作者：wl on 2017/9/18 16:19
 * 邮箱：wangl@ixinyongjia.com
 */
public abstract class BaseFragment extends Fragment implements BaseView, OnClickDefaultBtn {
    protected FrameLayout rootView;
    protected View mContentView;
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mContentView == null) {
            //初始化布局
            rootView = new FrameLayout(getActivity());
            mContentView = initView(inflater);
            rootView.addView(mContentView, getLayoutParams());
            //初始化数据
            initData();
        } else {
            // 不为null时，需要把自身从父布局中移除，因为ViewPager会再次添加
            ViewParent parent = rootView.getParent();
            if (parent != null && parent instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) parent;
                group.removeView(rootView);
            }
            updateData();
        }

        return rootView;
    }

    /**
     * @return 初始化布局,//子类强制重写，加载layout布局
     */
    protected abstract View initView(LayoutInflater inflater);

    /**
     * 初始化数据，只有第一次初始化Fragment才会调用
     */
    protected void initData() {

    }

    /**
     * 更新数据
     */
    protected void updateData() {

    }

    private FrameLayout.LayoutParams getLayoutParams() {
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return lp;
    }

    /*获取容器Activity*/
    public BaseActivity getBaseActivity() {
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            return (BaseActivity) activity;
        }
        return null;
    }


    @Override
    public boolean isNetworkerConnectHint() {
        BaseActivity baseActivity = getBaseActivity();
        if (baseActivity != null) {
            return baseActivity.isNetworkerConnectHint();
        }
        return NetworkUtil.isNetworkerConnect();
    }

    @Override
    public void showLoadingView() {
        BaseActivity baseActivity = getBaseActivity();
        if (baseActivity != null) {
            baseActivity.showLoadingView();
        }
    }

    @Override
    public void showLoadingView(int Type) {
        BaseActivity baseActivity = getBaseActivity();
        if (baseActivity != null) {
            baseActivity.showLoadingView(Type);
        }
    }

    @Override
    public void hideLoadingView() {
        BaseActivity baseActivity = getBaseActivity();
        if (baseActivity != null) {
            baseActivity.hideLoadingView();
        }
    }

    @Override
    public BaseTitleBar getTitleBar() {
        BaseActivity baseActivity = getBaseActivity();
        if (baseActivity != null) {
            return baseActivity.getTitleBar();
        }
        return null;
    }

    @Override
    public BaseStateBar getStateBar() {
        BaseActivity baseActivity = getBaseActivity();
        if (baseActivity != null) {
            return baseActivity.getStateBar();
        }
        return null;
    }

    @Override
    public void showHintAndFinish(String hint) {

    }

    @Override
    public void showHint(@Nullable String hintText) {
        BaseActivity baseActivity = getBaseActivity();
        if (baseActivity != null) {
            baseActivity.showHint(hintText);
        }
    }

    @Override
    public void showHint(String hintText, int color) {

    }

    /*********************缺省页DefaultView不复用，避免一个activity多个fragment的情况***********************/
    private DefaultView defaultView;

    @Override
    public DefaultView.DefaultViewBuild getDefaultViewBuild() {
        initDefaultView();
        return defaultView.getBuild();
    }

    @Override
    public void showDefaultView() {
        if (defaultView != null) {
            defaultView.show();
        }
    }

    @Override
    public void showErrorDefaultView(String text) {
        initDefaultView();
        defaultView.setErrorData(text);
    }

    @Override
    public void showNoDataDefaultView(String text) {
        initDefaultView();
        defaultView.setResultData(text);
    }

    @Override
    public void hideDefaultView() {
        if (defaultView != null) {
            defaultView.hide();
        }
    }

    @Override
    public boolean defaultViewIsShow() {
        if (defaultView == null) {
            return false;
        } else {
            return defaultView.isShow();
        }
    }

    private void initDefaultView() {
        if (defaultView == null) {
            defaultView = new DefaultView();
            defaultView.setClickListener(this);
            rootView.addView(defaultView.getRootView(), getLayoutParams());
        }
    }


    @Override
    public void onDefaultViewClick(int tag) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /*****************RxJava相关***************************************/

    @Override
    public void addDisposable(Disposable observer) {
        compositeDisposable.add(observer);
    }

    @Override
    public void removeDisposable(Disposable observer) {
        compositeDisposable.remove(observer);
    }

    @Override
    public void dispose() {
        compositeDisposable.dispose();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dispose();
        hideLoadingView();
//        Logger.d("onDestroy------%s", getClass().getName());
    }
}
