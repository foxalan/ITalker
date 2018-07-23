package com.example.alan.italker.frags.search;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alan.push.common.activities.BaseFragment;
import com.alan.push.common.activities.BasePresenterFragment;
import com.alan.push.common.widget.EmptyView;
import com.alan.push.common.widget.PortraitView;
import com.alan.push.common.widget.recycler.BaseRecyclerAdapter;
import com.bumptech.glide.Glide;
import com.example.alan.factory.model.card.UserCard;
import com.example.alan.factory.presenter.contact.FollowContract;
import com.example.alan.factory.presenter.contact.FollowPresenter;
import com.example.alan.factory.presenter.search.SearchContract;
import com.example.alan.factory.presenter.search.SearchUserPresenter;
import com.example.alan.italker.R;
import com.example.alan.italker.activities.PersonalActivity;
import com.example.alan.italker.activities.SearchActivity;

import net.qiujuer.genius.ui.Ui;
import net.qiujuer.genius.ui.compat.UiCompat;
import net.qiujuer.genius.ui.drawable.LoadingCircleDrawable;
import net.qiujuer.genius.ui.drawable.LoadingDrawable;

import java.util.List;

/**
 * @author alan
 *         Date  2018/7/21.
 *         Function :
 *         Issue :
 */

public class SearchUserFragment extends BasePresenterFragment<SearchContract.Presenter>
        implements SearchActivity.SearchFragment, SearchContract.UserView{


    EmptyView mEmptyView;
    RecyclerView mRecycler;

    private BaseRecyclerAdapter<UserCard> mAdapter = null;


    @Override
    protected void initWidget(View rootView) {
        super.initWidget(rootView);

        mEmptyView = rootView.findViewById(R.id.empty);
        mRecycler = rootView.findViewById(R.id.recycler);

        // 初始化Recycler
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.setAdapter(mAdapter = new BaseRecyclerAdapter<UserCard>() {
            @Override
            public BaseViewHolder<UserCard> onCreateBaseViewHolder(View root, int viewType) {
                return new SearchUserFragment.ViewHolder(root);
            }

            @Override
            protected int getItemViewType(int position, UserCard userCard) {
                // 返回cell的布局id
                return R.layout.cell_search_list;
            }


        });

        // 初始化占位布局
        mEmptyView.bind(mRecycler);
        setPlaceHolderView(mEmptyView);
    }

    @Override
    protected void initData() {
        super.initData();
        // 发起首次搜索
        search("");
    }

    @Override
    public void search(String content) {
        // Activity->Fragment->Presenter->Net
        mPresenter.search(content);
    }

    @Override
    public void onSearchDone(List<UserCard> userCards) {
        // 数据成功的情况下返回数据
        mAdapter.replace(userCards);
        // 如果有数据，则是OK，没有数据就显示空布局
        mPlaceHolderView.triggerOkOrEmpty(mAdapter.getItemCount() > 0);
    }


    @Override
    protected SearchContract.Presenter initPresenter() {
        return new SearchUserPresenter(this);
    }

    @Override
    public Object getLayoutView() {
        return R.layout.fragment_search_user;
    }


    /**
     * 每一个Cell的布局操作
     */
    class ViewHolder extends BaseRecyclerAdapter.BaseViewHolder<UserCard>
            implements FollowContract.View {

        PortraitView mPortraitView;
        TextView mName;
        ImageView mFollow;

        private FollowContract.Presenter mPresenter;


        public ViewHolder(View itemView) {
            super(itemView);
            mPortraitView = itemView.findViewById(R.id.im_portrait);
            mName = itemView.findViewById(R.id.txt_name);
            mFollow = itemView.findViewById(R.id.im_follow);

            // 当前View和Presenter绑定
            new FollowPresenter(this);
        }

        @Override
        public void onBind(UserCard userCard) {
            mPortraitView.setup(Glide.with(SearchUserFragment.this), userCard);
            mName.setText(userCard.getName());
            mFollow.setEnabled(!userCard.isFollow());

            mPortraitView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 显示信息
                    PersonalActivity.show(getContext(), mData.getId());
                }
            });

            mFollow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 发起关注
                    mPresenter.follow(mData.getId());
                }
            });

        }

        @Override
        public void showError(int str) {
            // 更改当前界面状态
            if (mFollow.getDrawable() instanceof LoadingDrawable) {
                // 失败则停止动画，并且显示一个圆圈
                LoadingDrawable drawable = (LoadingDrawable) mFollow.getDrawable();
                drawable.setProgress(1);
                drawable.stop();
            }
        }

        @Override
        public void showLoading() {
            int minSize = (int) Ui.dipToPx(getResources(), 22);
            int maxSize = (int) Ui.dipToPx(getResources(), 30);
            // 初始化一个圆形的动画的Drawable
            LoadingDrawable drawable = new LoadingCircleDrawable(minSize, maxSize);
            drawable.setBackgroundColor(0);

            int[] color = new int[]{UiCompat.getColor(getResources(), R.color.white_alpha_208)};
            drawable.setForegroundColor(color);
            // 设置进去
            mFollow.setImageDrawable(drawable);
            // 启动动画
            drawable.start();
        }

        @Override
        public void setPresenter(FollowContract.Presenter presenter) {
            mPresenter = presenter;
        }

        @Override
        public void onFollowSucceed(UserCard userCard) {
            // 更改当前界面状态
            if (mFollow.getDrawable() instanceof LoadingDrawable) {
                ((LoadingDrawable) mFollow.getDrawable()).stop();
                // 设置为默认的
                mFollow.setImageResource(R.drawable.sel_opt_done_add);
            }
            // 发起更新
            updateData(userCard);
        }
    }
}
