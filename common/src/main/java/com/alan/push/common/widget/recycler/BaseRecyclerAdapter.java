package com.alan.push.common.widget.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alan.push.common.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author alan
 *         Date  2018/6/23.
 *         Function :
 *         Issue :
 */

public abstract   class BaseRecyclerAdapter<Data> extends RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder<Data>>
        implements View.OnClickListener, View.OnLongClickListener ,AdapterCallBack<Data>{

    private  List<Data> mDataList = null;
    private AdapterListener<Data> mListener;

    /**
     * 构造函数模块
     */
    public BaseRecyclerAdapter() {
        this(null);
    }

    public BaseRecyclerAdapter(AdapterListener<Data> listener) {
        this(new ArrayList<Data>(), listener);
    }

    public BaseRecyclerAdapter(List<Data> dataList, AdapterListener<Data> listener) {
        this.mDataList = dataList;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public BaseViewHolder<Data> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        //约定viewType为View的ID
        View root = inflater.inflate(viewType,parent,false);
        BaseViewHolder<Data> viewHolder = onCreateBaseViewHolder(root,viewType);
        //添加点击事件
        root.setOnClickListener(this);
        root.setOnLongClickListener(this);

        //将ViewHolder放入到TAG中方便以后的操作
        root.setTag(R.id.id_recycler_holder,root);

        // 绑定callback
        viewHolder.callBack = this;

        return viewHolder;
    }

    /**
     * 创建ViewHolder
     * @param root
     * @param viewType
     * @return
     */
    public abstract BaseViewHolder<Data> onCreateBaseViewHolder(View root,int viewType);

    /**
     * 绑定ViewHolder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<Data> holder, int position) {
        Data data = mDataList.get(position);
        holder.bind(data);
    }

    /**
     * 插入一条数据并通知插入
     *
     * @param data Data
     */
    public void add(Data data) {
        mDataList.add(data);
        notifyItemInserted(mDataList.size() - 1);
    }

    /**
     * 插入一堆数据，并通知这段集合更新
     *
     * @param dataList Data
     */
    public void add(Data... dataList) {
        if (dataList != null && dataList.length > 0) {
            int startPos = mDataList.size();
            Collections.addAll(mDataList, dataList);
            notifyItemRangeInserted(startPos, dataList.length);
        }
    }

    /**
     * 插入一堆数据，并通知这段集合更新
     *
     * @param dataList Data
     */
    public void add(Collection<Data> dataList) {
        if (dataList != null && dataList.size() > 0) {
            int startPos = mDataList.size();
            mDataList.addAll(dataList);
            notifyItemRangeInserted(startPos, dataList.size());
        }
    }

    /**
     * 删除操作
     */
    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }

    /**
     * 替换为一个新的集合，其中包括了清空
     *
     * @param dataList 一个新的集合
     */
    public void replace(Collection<Data> dataList) {
        mDataList.clear();
        if (dataList == null || dataList.size() == 0){
            return;
        }
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    static abstract class BaseViewHolder<Data> extends RecyclerView.ViewHolder{

        private Data data;
        public AdapterCallBack<Data> callBack;

        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(Data data){
            this.data = data;
            onBind(data);
        }

        /**
         * 绑定数据
         * @param data
         */
        public abstract void onBind(Data data);

        public void updateData(Data data){
            if (callBack!=null){
                callBack.update(data,this);
            }
        }

    }


    /**
     * 设置适配器的监听
     *
     * @param adapterListener AdapterListener
     */
    public void setListener(AdapterListener<Data> adapterListener) {
        this.mListener = adapterListener;
    }

    @Override
    public void onClick(View v) {
        BaseViewHolder viewHolder = (BaseViewHolder) v.getTag(R.id.id_recycler_holder);
        if (this.mListener != null) {
            // 得到ViewHolder当前对应的适配器中的坐标
            int pos = viewHolder.getAdapterPosition();
            // 回掉方法
            this.mListener.onItemClick(viewHolder, mDataList.get(pos));
        }

    }

    @Override
    public boolean onLongClick(View v) {
        BaseViewHolder viewHolder = (BaseViewHolder) v.getTag(R.id.id_recycler_holder);
        if (this.mListener != null) {
            // 得到ViewHolder当前对应的适配器中的坐标
            int pos = viewHolder.getAdapterPosition();
            // 回掉方法
            this.mListener.onItemLongClick(viewHolder, mDataList.get(pos));
            return true;
        }
        return false;
    }

    /**
     * 我们的自定义监听器
     *
     * @param <Data> 范型
     */
    public interface AdapterListener<Data> {
        /**
         *当Cell点击的时候触发
         */
        void onItemClick(BaseViewHolder holder, Data data);

        /**
         *当Cell长按时触发
         */
        void onItemLongClick(BaseViewHolder holder, Data data);
    }
}
