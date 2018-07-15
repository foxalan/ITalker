package com.example.alan.italker.frags.user;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.alan.push.common.activities.BasePresenterFragment;
import com.alan.push.common.widget.PortraitView;
import com.example.alan.factory.presenter.user.UpdateInfoContract;
import com.example.alan.factory.presenter.user.UpdateInfoPresenter;
import com.example.alan.italker.MainActivity;
import com.example.alan.italker.R;

import net.qiujuer.genius.ui.widget.Loading;

/**
 * @author alan
 *         Date  2018/7/15.
 *         Function :
 *         Issue :
 */

public class UpdateInfoFragment extends BasePresenterFragment<UpdateInfoContract.Presenter>
        implements UpdateInfoContract.View{

    ImageView mSex;
    EditText mDesc;
    PortraitView mPortrait;
    Loading mLoading;
    Button mSubmit;

    private String mPortraitPath;
    private boolean isMan = true;


    @Override
    protected void initWidget(View rootView) {
        super.initWidget(rootView);
        mSex = rootView.findViewById(R.id.im_sex);
        mDesc = rootView.findViewById(R.id.edit_desc);
        mPortrait = rootView.findViewById(R.id.im_portrait);
        mLoading = rootView.findViewById(R.id.loading);
        mSubmit = rootView.findViewById(R.id.btn_submit);


    }

    @Override
    protected UpdateInfoContract.Presenter initPresenter() {
        return new UpdateInfoPresenter(this);
    }

    @Override
    public Object getLayoutView() {
        return R.layout.fragment_update;
    }

    @Override
    public void showError(int str) {
        super.showError(str);
        // 当需要显示错误的时候触发，一定是结束了

        // 停止Loading
        mLoading.stop();
        // 让控件可以输入
        mDesc.setEnabled(true);
        mPortrait.setEnabled(true);
        mSex.setEnabled(true);
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
        mDesc.setEnabled(false);
        mPortrait.setEnabled(false);
        mSex.setEnabled(false);
        // 提交按钮不可以继续点击
        mSubmit.setEnabled(false);
    }

    @Override
    public void updateSucceed() {
        // 更新成功跳转到主界面
        MainActivity.show(getContext());
        getActivity().finish();
    }
}
