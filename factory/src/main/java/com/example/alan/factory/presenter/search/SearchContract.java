package com.example.alan.factory.presenter.search;

import com.alan.push.common.factory.presenter.BaseContract;
import com.example.alan.factory.model.card.UserCard;

import java.util.List;

/**
 * @author alan
 *         Date  2018/7/21.
 *         Function :
 *         Issue :
 */

public interface SearchContract {

    interface Presenter extends BaseContract.Presenter {
        /**
         *搜索内容
         */
        void search(String content);
    }

    /**
     *搜索人的界面
     */
    interface UserView extends BaseContract.View<Presenter> {
        void onSearchDone(List<UserCard> userCards);
    }

    /**
     *搜索群的界面
     */
    interface GroupView extends BaseContract.View<Presenter> {
//        void onSearchDone(List<GroupCard> groupCards);
    }

}
