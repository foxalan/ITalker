package com.alan.push.common.activities;


import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.alan.push.common.R;

/**
 * @author alan
 *         Date  2018/7/21.
 *         Function :
 *         Issue :
 */

public abstract class BaseToolbarActivity extends BaseActivity{
    protected Toolbar mToolbar;


    @Override
    protected void initWidget() {
        super.initWidget();
        initToolbar((Toolbar) findViewById(R.id.toolbar));
    }

    /**
     * 初始化toolbar
     *
     * @param toolbar Toolbar
     */
    public void initToolbar(Toolbar toolbar) {
        mToolbar = toolbar;
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        initTitleNeedBack();
    }

    protected void initTitleNeedBack() {
        // 设置左上角的返回按钮为实际的返回效果
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }
}
