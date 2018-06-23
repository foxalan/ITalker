package com.alan.push.common.widget.recycler;

/**
 * @author alan
 *         Date  2018/6/23.
 *         Function :
 *         Issue :
 */

public interface AdapterCallBack<Data> {

    void update(Data data, BaseRecyclerAdapter.BaseViewHolder<Data> viewHolder);
}
