package com.alan.push.common.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * @author alan
 *         Date  2018/6/22.
 *         Function : 父Activity
 *         Issue :
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initWindow();

        if (initArgs(getIntent().getExtras())) {
            setContentView(getContentViewID());
            initWidget();
            initData();
        } else {
            finish();
        }

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
}
