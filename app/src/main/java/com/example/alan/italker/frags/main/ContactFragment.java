package com.example.alan.italker.frags.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alan.push.common.activities.BaseFragment;
import com.alan.push.common.activities.BasePresenterFragment;
import com.alan.push.common.widget.EmptyView;
import com.alan.push.common.widget.PortraitView;
import com.alan.push.common.widget.recycler.BaseRecyclerAdapter;
import com.bumptech.glide.Glide;
import com.example.alan.factory.model.db.User;
import com.example.alan.factory.presenter.contact.ContactContract;
import com.example.alan.factory.presenter.contact.ContactPresenter;
import com.example.alan.italker.R;

import static com.raizlabs.android.dbflow.config.FlowManager.getContext;

/**
 * @author alan
 *         Date  2018/7/8.
 *         Function :联系人列表
 *         Issue :
 */

public class ContactFragment extends BasePresenterFragment<ContactContract.Presenter>
        implements ContactContract.View {


    EmptyView mEmptyView;
    RecyclerView mRecycler;

    private BaseRecyclerAdapter<User> mAdapter;

    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initWidget(View rootView) {
        super.initWidget(rootView);

        mEmptyView = rootView.findViewById(R.id.empty);
        mRecycler = rootView.findViewById(R.id.recycler);

        // 初始化Recycler
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.setAdapter(mAdapter = new BaseRecyclerAdapter<User>() {
            @Override
            public BaseViewHolder<User> onCreateBaseViewHolder(View root, int viewType) {
                return new ContactFragment.ViewHolder(root);

            }

            @Override
            protected int getItemViewType(int position, User userCard) {
                // 返回cell的布局id
                return R.layout.cell_contact_list;
            }

        });

        // 点击事件监听
        mAdapter.setListener(new BaseRecyclerAdapter.AdapterListenerImpl<User>() {
            @Override
            public void onItemClick(BaseRecyclerAdapter.BaseViewHolder holder, User user) {
                // 跳转到聊天界面
                //   MessageActivity.show(getContext(), user);
            }
        });

        mEmptyView.bind(mRecycler);
        setPlaceHolderView(mEmptyView);

    }

    @Override
    public Object getLayoutView() {
        return R.layout.fragment_contact;
    }



    @Override
    protected ContactContract.Presenter initPresenter() {
        return new ContactPresenter(this);
    }

    @Override
    public BaseRecyclerAdapter<User> getRecyclerAdapter() {
        return mAdapter;
    }

    @Override
    public void onAdapterDataChanged() {
        // 进行界面操作
        mPlaceHolderView.triggerOkOrEmpty(mAdapter.getItemCount() > 0);
    }

    class ViewHolder extends BaseRecyclerAdapter.BaseViewHolder<User> {

        PortraitView mPortraitView;
        TextView mName;
        TextView mDesc;


        public ViewHolder(View itemView) {
            super(itemView);
            mPortraitView = itemView.findViewById(R.id.im_portrait);
            mName = itemView.findViewById(R.id.txt_name);
            mDesc = itemView.findViewById(R.id.txt_desc);
        }

        @Override
        public void onBind(User user) {
            mPortraitView.setup(Glide.with(ContactFragment.this), user);
            mName.setText(user.getName());
            mDesc.setText(user.getDesc());

            mPortraitView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //PersonalActivity.show(getContext(), mData.getId());
                }
            });

        }


    }
}
