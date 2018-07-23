package com.example.alan.italker.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alan.push.common.activities.BasePresenterToolbarActivity;
import com.alan.push.common.widget.PortraitView;
import com.bumptech.glide.Glide;
import com.example.alan.factory.model.db.User;
import com.example.alan.factory.presenter.contact.PersonalContract;
import com.example.alan.factory.presenter.contact.PersonalPresenter;
import com.example.alan.italker.R;

import net.qiujuer.genius.res.Resource;

/**
 * @author alan
 *         Date  2018/7/23.
 *         Function :
 *         Issue :
 */

public class PersonalActivity extends BasePresenterToolbarActivity<PersonalContract.Presenter>
        implements PersonalContract.View {

    private static final String BOUND_KEY_ID = "BOUND_KEY_ID";
    private String userId;

    ImageView mHeader;
    PortraitView mPortrait;
    TextView mName;
    TextView mDesc;
    TextView mFollows;
    TextView mFollowing;
    Button mSayHello;

    /**
     * 关注
     */
    private MenuItem mFollowItem;
    private boolean mIsFollowUser = false;

    public static void show(Context context, String userId) {
        Intent intent = new Intent(context, PersonalActivity.class);
        intent.putExtra(BOUND_KEY_ID, userId);
        context.startActivity(intent);
    }


    @Override
    protected boolean initArgs(Bundle bundle) {
        userId = bundle.getString(BOUND_KEY_ID);
        return !TextUtils.isEmpty(userId);
    }

    @Override
    public int getContentViewID() {
        return R.layout.activity_personal;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        mHeader = findViewById(R.id.im_header);
        mPortrait = findViewById(R.id.im_portrait);

        mName = findViewById(R.id.txt_name);
        mDesc = findViewById(R.id.txt_desc);
        mFollows = findViewById(R.id.txt_follows);
        mFollowing = findViewById(R.id.txt_following);
        mSayHello = findViewById(R.id.btn_say_hello);

        mSayHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 发起聊天的点击
                User user = mPresenter.getUserPersonal();
                if (user == null)
                    return;
                MessageActivity.show(PersonalActivity.this, user);
            }
        });

        setTitle("");
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.personal, menu);
        mFollowItem = menu.findItem(R.id.action_follow);
        changeFollowItemStatus();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_follow) {
            // TODO 进行关注操作
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    /**
     * 更改关注菜单状态
     */
    private void changeFollowItemStatus() {
        if (mFollowItem == null)
            return;

        // 根据状态设置颜色
        Drawable drawable = mIsFollowUser ? getResources()
                .getDrawable(R.drawable.ic_favorite) :
                getResources().getDrawable(R.drawable.ic_favorite_border);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, Resource.Color.WHITE);
        mFollowItem.setIcon(drawable);
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @SuppressLint("StringFormatMatches")
    @Override
    public void onLoadDone(User user) {
        if (user == null)
            return;
        mPortrait.setup(Glide.with(this), user);
        mName.setText(user.getName());
        mDesc.setText(user.getDesc());
        mFollows.setText(String.format(getString(R.string.label_follows), user.getFollows()));
        mFollowing.setText(String.format(getString(R.string.label_following), user.getFollowing()));
        hideLoading();
    }

    @Override
    public void allowSayHello(boolean isAllow) {
        mSayHello.setVisibility(isAllow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setFollowStatus(boolean isFollow) {
        mIsFollowUser = isFollow;
        changeFollowItemStatus();
    }

    @Override
    protected PersonalContract.Presenter initPresenter() {
        return new PersonalPresenter(this);
    }
}
