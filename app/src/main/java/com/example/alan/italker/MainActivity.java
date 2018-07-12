package com.example.alan.italker;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alan.push.common.activities.BaseActivity;
import com.alan.push.common.widget.PortraitView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.example.alan.italker.frgs.main.ActiveFragment;
import com.example.alan.italker.frgs.main.ContactFragment;
import com.example.alan.italker.frgs.main.GroupFragment;
import com.example.alan.italker.helper.NavHelper;

import net.qiujuer.genius.ui.widget.FloatActionButton;


/**
 * 主页
 * @author alan
 */

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        NavHelper.OnTabChangedListener<Integer>{

    View mLayAppbar;
    PortraitView mPortrait;
    TextView mTitle;
    FrameLayout mContainer;
    BottomNavigationView mNavigation;
    FloatActionButton mAction;

    private NavHelper<Integer> mNavHelper;


    @Override
    public int getContentViewID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mLayAppbar = findViewById(R.id.appbar);
        mPortrait = findViewById(R.id.im_portrait);
        mTitle = findViewById(R.id.txt_title);
        mContainer = findViewById(R.id.lay_container);
        mNavigation = findViewById(R.id.navigation);
        mAction = findViewById(R.id.btn_action);

        // 初始化底部辅助工具类
        mNavHelper = new NavHelper<>(this, R.id.lay_container,
                getSupportFragmentManager(), this);
        mNavHelper.add(R.id.action_home, new NavHelper.Tab<>(ActiveFragment.class, R.string.title_home))
                .add(R.id.action_group, new NavHelper.Tab<>(GroupFragment.class, R.string.title_group))
                .add(R.id.action_contact, new NavHelper.Tab<>(ContactFragment.class, R.string.title_contact));


        // 添加对底部按钮点击的监听
        mNavigation.setOnNavigationItemSelectedListener(this);

        Glide.with(this)
                .load(R.drawable.bg_src_morning)
                .centerCrop()
                .into(new ViewTarget<View, GlideDrawable>(mLayAppbar) {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        this.view.setBackground(resource.getCurrent());
                    }
                });
    }

    @Override
    protected void initData() {
        super.initData();

        // 从底部导中接管我们的Menu，然后进行手动的触发第一次点击
        Menu menu = mNavigation.getMenu();
        // 触发首次选中Home
        menu.performIdentifierAction(R.id.action_home, 0);

        // 初始化头像加载
    //    mPortrait.setup(Glide.with(this), Account.getUser());

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onTabChanged(NavHelper.Tab<Integer> newTab, NavHelper.Tab<Integer> oldTab) {

    }
}
