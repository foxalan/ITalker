package com.example.alan.factory.presenter.contact;

import com.alan.push.common.factory.presenter.BaseContract;
import com.example.alan.factory.model.card.UserCard;

/**
 * @author alan
 *         Date  2018/7/21.
 *         Function :
 *         Issue :
 */

public interface FollowContract {
    /**
     *任务调度者
     */
    interface Presenter extends BaseContract.Presenter {
        /**
         *关注一个人
         */
        void follow(String id);
    }

    interface View extends BaseContract.View<Presenter> {
        /**
         *成功的情况下返回一个用户的信息
         */
        void onFollowSucceed(UserCard userCard);
    }
}

