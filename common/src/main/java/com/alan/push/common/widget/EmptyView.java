package com.alan.push.common.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.alan.push.common.widget.convention.PlaceHolderView;

/**
 * @author alan
 *         Date  2018/7/8.
 *         Function : 简单的占位控件，
 *                          实现了显示一个空的图片显示，
 *                          可以和MVP配合显示没有数据，正在加载等状态
 *         Issue :
 */

public class EmptyView extends LinearLayout implements PlaceHolderView {


    public EmptyView(Context context) {
        super(context);
    }

    public EmptyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void triggerEmpty() {

    }

    @Override
    public void triggerNetError() {

    }

    @Override
    public void triggerError(int strRes) {

    }

    @Override
    public void triggerLoading() {

    }

    @Override
    public void triggerOk() {

    }

    @Override
    public void triggerOkOrEmpty(boolean isOk) {

    }
}
