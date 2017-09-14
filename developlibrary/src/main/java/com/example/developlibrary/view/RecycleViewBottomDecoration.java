package com.example.developlibrary.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class RecycleViewBottomDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public RecycleViewBottomDecoration(int space) {
        //this.space = XYJUiUtil.dip2px(space);
        this.space =space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

//        if (parent.getChildPosition(view) != 0){
//            outRect.top = space;
//        }else{
//            outRect.top = 0;
//        }
        outRect.bottom = space;
    }
}
