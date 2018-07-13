package com.example.alan.factory.presenter.contact;

import com.alan.push.common.factory.presenter.BaseContract;
import com.example.alan.factory.model.db.User;

/**
 * @author alan
 *         Date  2018/7/13.
 *         Function :
 *         Issue :
 */

public interface ContactContract {

    /**
     *什么都不需要额外定义，开始就是调用start即可
     */
    interface Presenter extends BaseContract.Presenter {

    }

    /**
     *都在基类完成了
     */
    interface View extends BaseContract.RecyclerView<Presenter, User> {

    }
}
