package com.example.alan.factory.presenter.contact;

import com.alan.push.common.factory.presenter.BaseContract;
import com.example.alan.factory.model.db.User;

/**
 * @author alan
 *         Date  2018/7/21.
 *         Function :
 *         Issue :
 */

public interface PersonalContract {

    interface Presenter extends BaseContract.Presenter {
        /**
         *获取用户信息
         */
        User getUserPersonal();
    }

    interface View extends BaseContract.View<Presenter> {
        /**
         *
         * @return
         */
        String getUserId();

        /**
         *加载数据完成
         */
        void onLoadDone(User user);

        /**
         *是否发起聊天
         */
        void allowSayHello(boolean isAllow);

        /**
         *设置关注状态
         */
        void setFollowStatus(boolean isFollow);
    }
}
