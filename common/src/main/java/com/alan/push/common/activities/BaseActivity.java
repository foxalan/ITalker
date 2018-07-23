package com.alan.push.common.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.alan.push.common.widget.convention.PlaceHolderView;

import java.util.List;

/**
 * @author alan
 *         Date  2018/6/22.
 *         Function : 父Activity
 *         Issue :
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected PlaceHolderView mPlaceHolderView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initWindow();

        if (initArgs(getIntent().getExtras())) {
            setContentView(getContentViewID());

            initBefore();

            initWidget();
            initData();
        } else {
            finish();
        }

    }


    /**
     * 初始化控件调用之前
     */
    protected void initBefore() {

    }


    /**
     * 得到View的ID
     *
     * @return 资源ID
     */
    public abstract int getContentViewID();

    /**
     * 初始化控件
     */
    protected void initWidget() {
    }

    /**
     * 初始化数据
     */
    protected void initData() {
    }

    protected boolean initArgs(Bundle bundle) {
        return true;
    }

    /**
     * 初始化Window
     */

    protected void initWindow() {
    }

    @Override
    public void onBackPressed() {

        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();

        if (fragmentList.size()>0){
            for(Fragment fragment:fragmentList){
                if (fragment instanceof  BaseFragment){
                    if(((BaseFragment) fragment).onBackPressed()){
                       return;
                    }
                }
            }
        }

        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        // 当点击界面导航返回时，Finish当前界面
        finish();
        return super.onSupportNavigateUp();
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
