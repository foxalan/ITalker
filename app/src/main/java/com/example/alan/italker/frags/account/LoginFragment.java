package com.example.alan.italker.frags.account;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alan.push.common.activities.BasePresenterFragment;
import com.example.alan.factory.presenter.account.LoginContract;
import com.example.alan.factory.presenter.account.LoginPresenter;
import com.example.alan.italker.MainActivity;
import com.example.alan.italker.R;

import net.qiujuer.genius.ui.widget.Loading;

/**
 * @author alan
 *         Date  2018/7/13.
 *         Function : 登入界面
 *         Issue :
 */

public class LoginFragment extends BasePresenterFragment<LoginContract.Presenter> implements LoginContract.View, View.OnClickListener {

    EditText mPhone;
    EditText mPassword;
    TextView mRegister;
    Loading mLoading;
    Button mSubmit;

    private AccountTrigger mAccountTrigger;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 拿到我们的Activity的引用
        mAccountTrigger = (AccountTrigger) context;
    }

    @Override
    protected void initWidget(View rootView) {
        super.initWidget(rootView);
        mPhone = rootView.findViewById(R.id.edit_phone);
        mPassword = rootView.findViewById(R.id.edit_password);
        mLoading = rootView.findViewById(R.id.loading);
        mSubmit = rootView.findViewById(R.id.btn_submit);
        mRegister = rootView.findViewById(R.id.txt_go_register);

        mSubmit.setOnClickListener(this);
        mRegister.setOnClickListener(this);
    }

    @Override
    public Object getLayoutView() {
        return R.layout.fragment_login;
    }


    @Override
    public void loginSuccess() {

        MainActivity.show(getContext());
        getActivity().finish();

    }


    @Override
    public void showError(int str) {
        super.showError(str);
        // 当需要显示错误的时候触发，一定是结束了

        // 停止Loading
        mLoading.stop();
        // 让控件可以输入
        mPhone.setEnabled(true);
        mPassword.setEnabled(true);
        // 提交按钮可以继续点击
        mSubmit.setEnabled(true);
    }

    @Override
    public void showLoading() {
        super.showLoading();

        // 正在进行时，正在进行注册，界面不可操作
        // 开始Loading
        mLoading.start();
        // 让控件不可以输入
        mPhone.setEnabled(false);
        mPassword.setEnabled(false);
        // 提交按钮不可以继续点击
        mSubmit.setEnabled(false);
    }



    @Override
    protected LoginContract.Presenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_go_register:
                mAccountTrigger.triggerView();
                break;
            case R.id.btn_submit:
                String phone = mPhone.getText().toString();
                String password = mPassword.getText().toString();
                // 调用P层进行注册
                mPresenter.login(phone, password);
                break;
            default:
                break;
        }
    }
}
