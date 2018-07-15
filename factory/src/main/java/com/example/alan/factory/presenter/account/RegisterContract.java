package com.example.alan.factory.presenter.account;

import com.alan.push.common.factory.presenter.BaseContract;

/**
 * @author alan
 *         Date  2018/7/15.
 *         Function :
 *         Issue :
 */

public interface RegisterContract {

    interface View extends BaseContract.View<Presenter> {
        /**
         *注册成功
         */
        void registerSuccess();
    }

    interface Presenter extends BaseContract.Presenter {
        /**
         *发起一个注册
         */
        void register(String phone, String name, String password);

        /**
         *检查手机号是否正确
         */
        boolean checkMobile(String phone);
    }
}
