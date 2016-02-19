package com.tiger.sectionadapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.tiger.sectionadapter.itemtype.SectionItemTypeZone;

/**
 * -----------------------------------------------------------
 * 版 权 ： BigTiger 版权所有 (c) 2015
 * 作 者 : BigTiger
 * 版 本 ： 1.0
 * 创建日期 ：2015/12/15
 * 描 述 ：用于同时管理多种ViewType的adapter, 每个ViewType用单独的小adapter管理
 * <p>
 * -------------------------------------------------------------
 **/
public class MultiTypeAdapter extends RecyclerView.Adapter<DataViewHolder> {
    private static final String TAG = "MultiTypeAdapter";
    private SectionItemTypeZone firstTypeZone;
    private SectionItemTypeZone lastTypeZone;

    public MultiTypeAdapter(RecyclerView.Adapter adapter){
        firstTypeZone = new SectionItemTypeZone();
        lastTypeZone = firstTypeZone;
        firstTypeZone.adapter = adapter;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return firstTypeZone.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        firstTypeZone.bindViewHolder(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        return firstTypeZone.getItemTypeByPosition(position);
    }

    @Override
    public int getItemCount() {
        return firstTypeZone.size();
    }

    /**
     *  添加一个新的adapter 到末尾, 默认取出adapter中ViewType的第一个值
     * @param adapter
     */
    public void addAdapter(RecyclerView.Adapter adapter) {
        SectionItemTypeZone typeZone = new SectionItemTypeZone();
        typeZone.adapter = adapter;
        lastTypeZone.setSupportItemTypeZone(typeZone);
        lastTypeZone = typeZone;
        notifyDataSetChanged();
    }
}
