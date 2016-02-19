package com.tiger.sectionadapter.itemtype;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.tiger.sectionadapter.DataViewHolder;

import java.util.HashSet;

/**
 * -----------------------------------------------------------
 * 版 权 ： BigTiger 版权所有 (c) 2015
 * 作 者 : BigTiger
 * 版 本 ： 1.0
 * 创建日期 ：2015/12/16
 * 描 述 ：SectionItemTypeZone表示一个区段的数据, 比如首页店铺的店铺分类,是一个区段;场景分类是一个区段
 * <p/>
 * -------------------------------------------------------------
 **/
public class SectionItemTypeZone implements ItemTypeZone {
    private static final String TAG = "SectionItemTypeZone";
    private ItemTypeZone upperItemTypeZone;
    public RecyclerView.Adapter<DataViewHolder> adapter;
    private HashSet<Integer> types = new HashSet<>();

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
        int type = position < length() ?
                adapter.getItemViewType(position) :
                upperItemTypeZone.getItemTypeByPosition(position - length());
        if (position < length()) {
            types.add(type);
        }
        return type;
    }

    @Override
    public DataViewHolder createViewHolder(ViewGroup parent, int viewType) {
        for (Integer type : types) {
            Log.i(TAG, type + "");
        }
        Log.i(TAG, "createViewHolder： " + viewType + "   isCon:" + types.contains(viewType));
        if (types.contains(viewType)) {
            return adapter.onCreateViewHolder(parent, viewType);
        }
        return upperItemTypeZone.createViewHolder(parent, viewType);
    }

    @Override
    public void setSupportItemTypeZone(ItemTypeZone typeZone) {
        upperItemTypeZone = typeZone;
    }

    /**
     *
     * @return 当前区域以及supportItemZone所包含的数据总和
     */
    @Override
    public int size(){
        return (adapter == null ? 0: adapter.getItemCount())
                + (upperItemTypeZone == null ? 0: upperItemTypeZone.size());
    }

    /**
     * 当前区域包含的数据长度
     * @return 当前区域包含的数据长度
     */
    private int length() {
        return adapter == null ? 0: adapter.getItemCount();
    }
}
