package com.example.alan.italker.frags.user;

import android.graphics.drawable.Drawable;
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
        implements UpdateInfoContract.View, View.OnClickListener {

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

        mSubmit.setOnClickListener(this);
        rootView.findViewById(R.id.im_portrait).setOnClickListener(this);
        rootView.findViewById(R.id.im_sex).setOnClickListener(this);


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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                break;
            case R.id.im_sex:
                setUserSex();
                break;
            case R.id.im_portrait:
                setUserPortrait();
                break;
            default:
                break;
        }
    }

    /**
     * 设置性别
     */
    private void setUserSex() {

        // 性别图片点击的时候触发
        isMan = !isMan;
        // 反向性别
        Drawable drawable = getResources().getDrawable(isMan ?
                R.drawable.ic_sex_man : R.drawable.ic_sex_woman);
        mSex.setImageDrawable(drawable);
        // 设置背景的层级，切换颜色
        mSex.getBackground().setLevel(isMan ? 0 : 1);
    }

    private void setUserPortrait() {
//        new GalleryFragment()
//                .setListener(new GalleryFragment.OnSelectedListener() {
//                    @Override
//                    public void onSelectedImage(String path) {
//                        UCrop.Options options = new UCrop.Options();
//                        // 设置图片处理的格式JPEG
//                        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
//                        // 设置压缩后的图片精度
//                        options.setCompressionQuality(96);
//
//                        // 得到头像的缓存地址
//                        File dPath = Application.getPortraitTmpFile();
//
//                        // 发起剪切
//                        UCrop.of(Uri.fromFile(new File(path)), Uri.fromFile(dPath))
//                                .withAspectRatio(1, 1) // 1比1比例
//                                .withMaxResultSize(520, 520) // 返回最大的尺寸
//                                .withOptions(options) // 相关参数
//                                .start(getActivity());
//                    }
//                })
//                // show 的时候建议使用getChildFragmentManager，
//                // tag GalleryFragment class 名
//                .show(getChildFragmentManager(), GalleryFragment.class.getName());
    }
}
