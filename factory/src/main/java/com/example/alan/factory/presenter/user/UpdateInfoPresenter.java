package com.example.alan.factory.presenter.user;

import com.alan.push.common.factory.data.DataSource;
import com.alan.push.common.factory.presenter.BasePresenter;
import com.example.alan.factory.model.card.UserCard;

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
    public void update(String imgUrl, String desc, boolean isMan) {

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
