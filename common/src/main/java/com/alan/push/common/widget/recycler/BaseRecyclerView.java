package com.alan.push.common.widget.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alan.push.common.R;

import java.util.List;

/**
 * @author alan
 *         Date  2018/6/23.
 *         Function :
 *         Issue :
 */

public abstract   class BaseRecyclerView<Data> extends RecyclerView.Adapter<BaseRecyclerView.BaseViewHolder<Data>> implements View.OnClickListener, View.OnLongClickListener {

    private final List<Data> dataList = null;


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

        return viewHolder;
    }

    /**
     * 创建ViewHolder
     * @param root
     * @param viewType
     * @return
     */
    public abstract BaseViewHolder<Data> onCreateBaseViewHolder(View root,int viewType);

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<Data> holder, int position) {
        Data data = dataList.get(position);
        holder.bind(data);
    }

    @Override
    public int getItemCount() {

        return dataList.size();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    static abstract class BaseViewHolder<Data> extends RecyclerView.ViewHolder{

        private Data data;
        private AdapterCallBack<Data> callBack;

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
}
