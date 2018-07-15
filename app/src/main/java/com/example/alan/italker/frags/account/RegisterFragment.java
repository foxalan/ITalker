package com.example.alan.italker.frags.account;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alan.push.common.activities.BasePresenterFragment;
import com.alan.push.common.factory.presenter.BaseContract;
import com.example.alan.factory.presenter.account.RegisterContract;
import com.example.alan.factory.presenter.account.RegisterPresenter;
import com.example.alan.italker.R;

import net.qiujuer.genius.ui.widget.Loading;

/**
 * @author alan
 *         Date  2018/7/13.
 *         Function :
 *         Issue :
 */

public class RegisterFragment extends BasePresenterFragment<RegisterContract.Presenter> implements RegisterContract.View, View.OnClickListener {

    private AccountTrigger mAccountTrigger;

    EditText mPhone;
    EditText mName;
    EditText mPassword;
    Loading mLoading;
    Button mSubmit;
    TextView mTxtGoLogin;

    @Override
    protected void initWidget(View rootView) {
        super.initWidget(rootView);

        mPhone = rootView.findViewById(R.id.edit_phone);
        mName = rootView.findViewById(R.id.edit_name);
        mPassword = rootView.findViewById(R.id.edit_password);
        mLoading = rootView.findViewById(R.id.loading);
        mSubmit = rootView.findViewById(R.id.btn_submit);
        mTxtGoLogin = rootView.findViewById(R.id.txt_go_login);

        mTxtGoLogin.setOnClickListener(this);
        mSubmit.setOnClickListener(this);

    }

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 拿到我们的Activity的引用
        mAccountTrigger = (AccountTrigger) context;
    }

    @Override
    public Object getLayoutView() {
        return R.layout.fragment_register;
    }

    @Override
    public void registerSuccess() {

    }

    @Override
    protected RegisterContract.Presenter initPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                String phone = mPhone.getText().toString();
                String name = mName.getText().toString();
                String password = mPassword.getText().toString();
                // 调用P层进行注册
                mPresenter.register(phone, name, password);
                break;
            case R.id.txt_go_login:
                mAccountTrigger.triggerView();
                break;
            default:
                break;
        }
    }

    @Override
    public void showError(int str) {
        super.showError(str);
        // 当需要显示错误的时候触发，一定是结束了

        // 停止Loading
        mLoading.stop();
        // 让控件可以输入
        mPhone.setEnabled(true);
        mName.setEnabled(true);
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
        mName.setEnabled(false);
        mPassword.setEnabled(false);
        // 提交按钮不可以继续点击
        mSubmit.setEnabled(false);

    }
}
