package com.example.developlibrary.utils;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.List;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS;
import static java.util.Arrays.asList;

/**
 * 作者：wl on 2017/9/14 15:06
 * 邮箱：wangl@ixinyongjia.com
 */
public class KeyboardHelper {
    public static void hideKeyBoardWhenNotInView(final MotionEvent event, View... views) {
        boolean shouldNotHide = false;
        for (View view : views) {
            if (isTouchInView(event, view)) {
                shouldNotHide = true;
                break;
            }
        }
        List<View> viewList = asList(views);
        if (!shouldNotHide) {
            hideKeyboard(viewList.get(0));
        }
    }

    public static void hideKeyBoardWhenNotInView(final MotionEvent event, boolean focusChange, View... views) {
        boolean shouldNotHide = false;
        for (View view : views) {
            if (isTouchInView(event, view)) {
                shouldNotHide = true;
                break;
            }
        }
        List<View> viewList = asList(views);
        if (!shouldNotHide) {
            focusChange = true;
            hideKeyboard(viewList.get(0));
        }
    }

    public static void hideKeyBoardWhenNotInView(MotionEvent event, View view) {
        if (view == null) {
            return;
        }

        boolean shouldHide = !isTouchInView(event, view);

        if (shouldHide) {
            hideKeyboard(view);
        }
    }

    public static void hideKeyboard(View view) {
        InputMethodManager im = (InputMethodManager) UiUtil.getContext().getSystemService(INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(view.getWindowToken(), HIDE_NOT_ALWAYS);
    }

    public static boolean isTouchInView(MotionEvent event, View view) {
        int[] location = {0, 0};

        view.getId();
        view.getLocationInWindow(location);

        int left = location[0];
        int top = location[1];
        int bottom = top + view.getHeight();
        int right = left + view.getWidth();

        return event.getRawX() > left && event.getRawX() < right && event.getRawY() > top && event.getRawY() < bottom;
    }

    public static void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) UiUtil.getContext().getSystemService(INPUT_METHOD_SERVICE);
        if (isDisable(imm)) return;
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }

    public static void showOrHiddenKeyboard() {
        InputMethodManager imm = (InputMethodManager) UiUtil.getContext().getSystemService(INPUT_METHOD_SERVICE);
        if (isDisable(imm)) return;
        imm.toggleSoftInput(0, HIDE_NOT_ALWAYS);
    }

    private static boolean isDisable(InputMethodManager imm) {
        return imm.getEnabledInputMethodList() == null || imm.getEnabledInputMethodList().isEmpty();
    }

    public static void setCursorTouchVisible(EditText... et) {
        if (et != null && et.length > 0) {
            for (int i = 0; i < et.length; i++) {
                EditText editText = et[i];
                if (editText != null) {
                    editText.setCursorVisible(false);
                    editText.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            EditText et = (EditText) v;
                            et.setCursorVisible(true);
                            return false;
                        }
                    });
                }
            }
        }
    }

    /**
     * @param root         最外层布局，需要调整的布局
     * @param scrollToView 被键盘遮挡的scrollToView，滚动root,使scrollToView在root可视区域的底部
     */
    private int srollhight = 0;

    protected void controlKeyboardLayout(final View root, final View scrollToView) {
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                //获取root在窗体的可视区域
                root.getWindowVisibleDisplayFrame(rect);
                //获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
                int totalHight = root.getRootView().getHeight();
                int rootInvisibleHeight = totalHight - rect.bottom;
                //若不可视区域高度大于100，则键盘显示

                if (rootInvisibleHeight >= totalHight / 3) {
                    int[] location = new int[2];
                    //获取scrollToView在窗体的坐标
                    scrollToView.getLocationInWindow(location);
                    //计算root滚动高度，使scrollToView在可见区域
                    int srollHeight = (location[1] + scrollToView.getHeight()) - rect.bottom;
                    if (srollhight == 0) {
                        srollhight = srollHeight;
                        // + AppUtils.dip2px(mContext, 30);
                    }
                    root.scrollTo(0, srollhight);
                } else {
                    //键盘隐藏
                    root.scrollTo(0, 0);
                }
            }
        });
    }
}
