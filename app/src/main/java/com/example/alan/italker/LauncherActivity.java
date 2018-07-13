package com.example.alan.italker;

import android.accounts.Account;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;


import com.alan.push.common.activities.BaseActivity;
import com.example.alan.italker.frags.assist.PermissionsFragment;

import net.qiujuer.genius.res.Resource;
import net.qiujuer.genius.ui.compat.UiCompat;

/**
 * @author alan
 *         Date  2018/7/12.
 *         Function : 启动页
 *         Issue :
 */

public class LauncherActivity extends BaseActivity {

    private ColorDrawable mBgDrawable;

    @Override
    public int getContentViewID() {
        return R.layout.activity_launcher;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initWidget() {
        super.initWidget();

        View root = findViewById(R.id.activity_launch);
        // 获取颜色
        int color = UiCompat.getColor(getResources(), R.color.colorPrimary);
        // 创建一个Drawable
        ColorDrawable drawable = new ColorDrawable(color);
        // 设置给背景
        root.setBackground(drawable);
        mBgDrawable = drawable;

    }

    @Override
    protected void initData() {
        super.initData();


        // 动画进入到50%等待PushId获取到
        startAnim(0.5f, new Runnable() {
            @Override
            public void run() {
                // 检查等待状态
                waitPushReceiverId();
            }
        });

    }


    /**
     * 等待个推框架对我们的PushId设置好值
     */
    private void waitPushReceiverId() {

        skip();



//        if (Account.isLogin()) {
//            // 已经登录情况下，判断是否绑定
//            // 如果没有绑定则等待广播接收器进行绑定
//            if (Account.isBind()) {
//                skip();
//                return;
//            }
//        } else {
//            // 没有登录
//            // 如果拿到了PushId, 没有登录是不能绑定PushId的
//            if (!TextUtils.isEmpty(Account.getPushId())) {
//                // 跳转
//                skip();
//                return;
//            }
//        }

        // 循环等待
    }


    /**
     * 在跳转之前需要把剩下的50%进行完成
     */
    private void skip() {
        startAnim(1f, new Runnable() {
            @Override
            public void run() {
                reallySkip();
            }
        });
    }

    /**
     * 真实的跳转
     */
    private void reallySkip() {
        // 权限检测，跳转
        if (PermissionsFragment.haveAll(this, getSupportFragmentManager())) {
            // 检查跳转到主页还是登录
            MainActivity.show(this);
            finish();
        }
    }



    /**
     * 给背景设置一个动画
     *
     * @param endProgress 动画的结束进度
     * @param endCallback 动画结束时触发
     */
    private void startAnim(float endProgress, final Runnable endCallback) {
        // 获取一个最终的颜色
        int finalColor = Resource.Color.WHITE;
        ArgbEvaluator evaluator = new ArgbEvaluator();
        int endColor = (int) evaluator.evaluate(endProgress, mBgDrawable.getColor(), finalColor);
        // 构建一个属性动画
        ValueAnimator valueAnimator = ObjectAnimator.ofObject(this, property, evaluator, endColor);
        valueAnimator.setDuration(1500);
        valueAnimator.setIntValues(mBgDrawable.getColor(), endColor);
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // 结束时触发
                endCallback.run();
            }
        });
        valueAnimator.start();
    }


    private final Property<LauncherActivity, Object> property = new Property<LauncherActivity, Object>(Object.class, "color") {
        @Override
        public void set(LauncherActivity object, Object value) {
            object.mBgDrawable.setColor((Integer) value);
        }

        @Override
        public Object get(LauncherActivity object) {
            return object.mBgDrawable.getColor();
        }
    };

}
