package com.alan.push.common.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alan.push.common.widget.convention.PlaceHolderView;

/**
 * @author alan
 *         Date  2018/6/22.
 *         Function : 父Fragment
 *         Issue :
 */

public abstract class BaseFragment extends Fragment {

    protected PlaceHolderView mPlaceHolderView;

    /**
     * 标示是否第一次初始化数据
     */
    protected boolean mIsFirstInitData = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        initArgs(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView;

        Object viewObject = getLayoutView();
        if (viewObject instanceof Integer) {
            rootView = inflater.inflate((Integer) viewObject, container, false);
        } else if (viewObject instanceof View) {
            rootView = (View) viewObject;
        } else {
            throw new NullPointerException("fragment view type error");
        }

        initWidget(rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mIsFirstInitData) {
            // 触发一次以后就不会触发
            mIsFirstInitData = false;
            // 触发
            onFirstInit();
        }
        initData();
    }

    /**
     * 当首次初始化数据的时候会调用的方法
     */
    protected void onFirstInit() {

    }


    /**
     * Fragment的布局
     *
     * @return
     */
    public abstract Object getLayoutView();

    /**
     * 初始化控件
     *
     * @param rootView
     */
    protected void initWidget(View rootView) {
    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    protected void initArgs(Bundle bundle) {
    }

    /**
     * 返回按键fa
     *
     * @return
     */
    protected boolean onBackPressed() {
        return false;
    }

    /**
     * 设置占位布局
     *
     * @param placeHolderView 继承了占位布局规范的View
     */
    public void setPlaceHolderView(PlaceHolderView placeHolderView) {
        this.mPlaceHolderView = placeHolderView;
    }

}
