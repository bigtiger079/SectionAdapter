package com.tiger.sectionadapter.itemtype;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.tiger.sectionadapter.DataViewHolder;


/**
 * -----------------------------------------------------------
 * 版 权 ： BigTiger 版权所有 (c) 2015
 * 作 者 : BigTiger
 * 版 本 ： 1.0
 * 创建日期 ：2015/12/9
 * 描 述 ：普通视图区域的实现
 * <p>
 * -------------------------------------------------------------
 **/
public class NormalItemTypeZone implements ItemTypeZone {
    private static final String TAG = "NormalItemTypeZone";
    private ItemTypeZone upperItemTypeZone;
    public RecyclerView.Adapter<DataViewHolder> adapter;
    @Override
    public int getItemZoneTypeByPosition(int position) {
        return position < length() ?
                ITEM_TYPE_OF_NORMAL :
                upperItemTypeZone.getItemZoneTypeByPosition(position - length());
    }

    @Override
    public void bindViewHolder(DataViewHolder holder, int position) {
        if (position < length()) {
            adapter.onBindViewHolder(holder, position);
        } else {
            upperItemTypeZone.bindViewHolder(holder, position - length());
        }
    }

    @Override
    public int getItemTypeByPosition(int position) {
        return position < length() ?
                adapter.getItemViewType(position) :
                upperItemTypeZone.getItemTypeByPosition(position - length());
    }

    @Override
    public DataViewHolder createViewHolder(ViewGroup parent, int viewType) {
        if ((viewType & ITEM_TYPE_OF_FOOTER) != ITEM_TYPE_OF_FOOTER ) {
            return adapter.createViewHolder(parent, viewType);
        }
        return upperItemTypeZone.createViewHolder(parent, viewType);
    }

    @Override
    public void setSupportItemTypeZone(ItemTypeZone typeZone) {
        upperItemTypeZone = typeZone;
    }

    @Override
    public int size(){
        return (adapter == null ? 0: adapter.getItemCount())
                + (upperItemTypeZone == null ? 0: upperItemTypeZone.size());
    }

    private int length() {
        return adapter == null ? 0: adapter.getItemCount();
    }
}
