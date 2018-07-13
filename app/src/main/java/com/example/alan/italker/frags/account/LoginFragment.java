package com.example.alan.italker.frags.account;

import com.alan.push.common.activities.BasePresenterFragment;
import com.alan.push.common.factory.presenter.BaseContract;
import com.example.alan.italker.R;

/**
 * @author alan
 *         Date  2018/7/13.
 *         Function : 登入界面
 *         Issue :
 */

public class LoginFragment extends BasePresenterFragment{



    @Override
    public Object getLayoutView() {
        return R.layout.fragment_login;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }
}
