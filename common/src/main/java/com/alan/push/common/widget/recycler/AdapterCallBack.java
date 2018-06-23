package com.alan.push.common.widget.recycler;

import android.support.v7.widget.RecyclerView;

/**
 * @author alan
 *         Date  2018/6/23.
 *         Function :
 *         Issue :
 */

public interface AdapterCallBack<Data> {

    void update(Data data, BaseRecyclerView.BaseViewHolder<Data> viewHolder);
}
