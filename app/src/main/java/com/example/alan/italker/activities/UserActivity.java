package com.example.alan.italker.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.widget.ImageView;

import com.alan.push.common.activities.Application;
import com.alan.push.common.activities.BaseActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.example.alan.italker.R;
import com.example.alan.italker.frags.user.UpdateInfoFragment;
import com.yalantis.ucrop.UCrop;

import net.qiujuer.genius.ui.compat.UiCompat;

/**
 * @author alan
 *         Date  2018/7/15.
 *         Function : 用户信息页提供信息修改
 *         Issue :
 */

public class UserActivity extends BaseActivity{

    private UpdateInfoFragment mCurFragment;
    ImageView mBg;

    public static void show(Context context){
        context.startActivity(new Intent(context,UserActivity.class));
    }


    @Override
    public int getContentViewID() {
        return R.layout.activity_user;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        mBg = findViewById(R.id.im_bg);
        mCurFragment = new UpdateInfoFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.lay_container, mCurFragment)
                .commit();

        // 初始化背景
        Glide.with(this)
                .load(R.drawable.bg_src_tianjin)
                .centerCrop() //居中剪切
                .into(new ViewTarget<ImageView, GlideDrawable>(mBg) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        // 拿到glide的Drawable
                        Drawable drawable = resource.getCurrent();
                        // 使用适配类进行包装
                        drawable = DrawableCompat.wrap(drawable);
                        drawable.setColorFilter(UiCompat.getColor(getResources(), R.color.colorAccent),
                                PorterDuff.Mode.SCREEN);
                        // 设置着色的效果和颜色，蒙板模式
                        // 设置给ImageView
                        this.view.setImageDrawable(drawable);
                    }
                });

    }
}
