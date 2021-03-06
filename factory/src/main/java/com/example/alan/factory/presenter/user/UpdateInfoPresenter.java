package com.example.alan.factory.presenter.user;

import android.text.TextUtils;
import android.util.Log;

import com.alan.push.common.factory.data.DataSource;
import com.alan.push.common.factory.presenter.BasePresenter;
import com.example.alan.factory.Factory;
import com.example.alan.factory.R;
import com.example.alan.factory.data.helper.UserHelper;
import com.example.alan.factory.model.api.user.UserUpdateModel;
import com.example.alan.factory.model.card.UserCard;
import com.example.alan.factory.model.db.User;
import com.example.alan.factory.net.UploadHelper;

import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

/**
 * @author alan
 *         Date  2018/7/15.
 *         Function :
 *         Issue :
 */

public class UpdateInfoPresenter extends BasePresenter<UpdateInfoContract.View> implements UpdateInfoContract.Presenter, DataSource.Callback<UserCard> {

    public UpdateInfoPresenter(UpdateInfoContract.View view) {
        super(view);
    }

    @Override
    public void update(final String imgUrl, final String desc, final boolean isMan) {
            start();
        final UpdateInfoContract.View view = getView();

        if (TextUtils.isEmpty(imgUrl) || TextUtils.isEmpty(desc)) {
            view.showError(R.string.data_account_update_invalid_parameter);
        } else {
            // 上传头像
            Factory.runOnAsync(new Runnable() {
                @Override
                public void run() {
                    String url = UploadHelper.uploadPortrait(imgUrl);
                    if (TextUtils.isEmpty(url)) {
                        // 上传失败
                        view.showError(R.string.data_upload_error);
                    } else {
                        // 构建Model
                        UserUpdateModel model = new UserUpdateModel("", url, desc,
                                isMan ? User.SEX_MAN : User.SEX_WOMAN);
                        // 进行网络请求，上传
                        Log.e("italker",model.toString());
                        UserHelper.update(model, UpdateInfoPresenter.this);
                    }
                }
            });
        }

    }

    @Override
    public void onDataLoaded(UserCard userCard) {
        final UpdateInfoContract.View view = getView();
        if (view == null){
            return;
        }
        // 强制执行在主线程中
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                view.updateSucceed();
            }
        });
    }

    @Override
    public void onDataNotAvailable(final int strRes) {
        final UpdateInfoContract.View view = getView();
        if (view == null){
            return;
        }
        // 强制执行在主线程中
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                view.showError(strRes);
            }
        });
    }
}
