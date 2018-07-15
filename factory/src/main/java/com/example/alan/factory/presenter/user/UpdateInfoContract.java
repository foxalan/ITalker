package com.example.alan.factory.presenter.user;

import com.alan.push.common.factory.presenter.BaseContract;

/**
 * @author alan
 *         Date  2018/7/15.
 *         Function :
 *         Issue :
 */

public interface UpdateInfoContract {

    interface Presenter extends BaseContract.Presenter {
        void update(String imgUrl, String desc, boolean isMan);
    }

    interface View extends BaseContract.View<Presenter> {

        void updateSucceed();
    }
}
