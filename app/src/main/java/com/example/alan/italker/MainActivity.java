package com.example.alan.italker;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alan.push.common.activities.BaseActivity;
import com.alan.push.common.widget.PortraitView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.example.alan.factory.persistence.Account;
import com.example.alan.italker.activities.SearchActivity;
import com.example.alan.italker.activities.UserActivity;
import com.example.alan.italker.frags.main.ActiveFragment;
import com.example.alan.italker.frags.main.ContactFragment;
import com.example.alan.italker.frags.main.GroupFragment;
import com.example.alan.italker.helper.NavHelper;

import net.qiujuer.genius.ui.Ui;
import net.qiujuer.genius.ui.widget.FloatActionButton;

import java.util.Objects;


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
    protected boolean initArgs(Bundle bundle) {
        if (Account.isComplete()) {
            // 判断用户信息是否完全，完全则走正常流程
            return super.initArgs(bundle);
        } else {
            UserActivity.show(this);
            return false;
        }
    }

    public static void show(Context context){
        context.startActivity(new Intent(context, MainActivity.class));
    }

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
        mNavHelper = new NavHelper<>(this, R.id.lay_container, getSupportFragmentManager(), this);
        mNavHelper.add(R.id.action_home, new NavHelper.Tab<>(ActiveFragment.class, R.string.title_home))
                          .add(R.id.action_group, new NavHelper.Tab<>(GroupFragment.class, R.string.title_group))
                          .add(R.id.action_contact, new NavHelper.Tab<>(ContactFragment.class, R.string.title_contact));


        // 添加对底部按钮点击的监听
        mNavigation.setOnNavigationItemSelectedListener(this);
        mAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 浮动按钮点击时，判断当前界面是群还是联系人界面
                // 如果是群，则打开群创建的界面
                if (Objects.equals(mNavHelper.getCurrentTab().extra, R.string.title_group)) {
                    // TODO 打开群创建界面
                } else {
                    // 如果是其他，都打开添加用户的界面
                    SearchActivity.show(MainActivity.this, SearchActivity.TYPE_USER);
                }
            }
        });

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

    /**
     * 当我们的底部导航被点击的时候触发
     *
     * @param item MenuItem
     * @return True 代表我们能够处理这个点击
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // 转接事件流到工具类中
        return mNavHelper.performClickMenu(item.getItemId());
    }

    @Override
    public void onTabChanged(NavHelper.Tab<Integer> newTab, NavHelper.Tab<Integer> oldTab) {

        // 从额外字段中取出我们的Title资源Id
        mTitle.setText(newTab.extra);


        // 对浮动按钮进行隐藏与显示的动画
        float transY = 0;
        float rotation = 0;
        if (Objects.equals(newTab.extra, R.string.title_home)) {
            // 主界面时隐藏
            transY = Ui.dipToPx(getResources(), 76);
        } else {
            // transY 默认为0 则显示
            if (Objects.equals(newTab.extra, R.string.title_group)) {
                // 群
                mAction.setImageResource(R.drawable.ic_group_add);
                rotation = -360;
            } else {
                // 联系人
                mAction.setImageResource(R.drawable.ic_contact_add);
                rotation = 360;
            }
        }

        // 开始动画
        // 旋转，Y轴位移，弹性差值器，时间
        mAction.animate()
                .rotation(rotation)
                .translationY(transY)
                .setInterpolator(new AnticipateOvershootInterpolator(1))
                .setDuration(480)
                .start();


    }




}
