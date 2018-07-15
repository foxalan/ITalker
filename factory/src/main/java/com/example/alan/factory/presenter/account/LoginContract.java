package com.example.alan.factory.presenter.account;

import com.alan.push.common.factory.presenter.BaseContract;

/**
 * @author alan
 *         Date  2018/7/13.
 *         Function :
 *         Issue :
 */

public interface LoginContract {

    interface View extends BaseContract.View<Presenter> {
        /**
         *登录成功
         */
        void loginSuccess();

    }


    interface Presenter extends BaseContract.Presenter {
        /**
         *发起一个登录
         */
        void login(String phone, String password);
    }
}
