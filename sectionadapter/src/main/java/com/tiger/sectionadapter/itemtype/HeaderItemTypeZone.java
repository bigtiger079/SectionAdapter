package com.tiger.sectionadapter.itemtype;

import android.util.Log;
import android.view.ViewGroup;


import com.tiger.sectionadapter.DataViewHolder;
import com.tiger.sectionadapter.ItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * -----------------------------------------------------------
 * 版 权 ： BigTiger 版权所有 (c) 2015
 * 作 者 : BigTiger
 * 版 本 ： 1.0
 * 创建日期 ：2015/12/9
 * 描 述 ：头部视图区域的实现
 * <p>
 * -------------------------------------------------------------
 **/
public class HeaderItemTypeZone implements ItemTypeZone {
    private ItemTypeZone upperItemTypeZone;
    public List<ItemView> headers = new ArrayList<>();
    private static final String TAG = "HeaderItemTypeZone";
    @Override
    public int getItemZoneTypeByPosition(int position) {
        return position < length() ?
                ITEM_TYPE_OF_HEADER :
                upperItemTypeZone.getItemZoneTypeByPosition(position - length());
    }

    @Override
    public void bindViewHolder(DataViewHolder holder, int position) {
        if (position < length()) {
            headers.get(position).bindViewHolder(holder, position);
        } else {
            upperItemTypeZone.bindViewHolder(holder, position - length());
        }
    }

    @Override
    public int getItemTypeByPosition(int position) {

        return position < length() ?
                headers.get(position).ItemViewType() :
                upperItemTypeZone.getItemTypeByPosition(position - length()) ;
    }

    @Override
    public DataViewHolder createViewHolder(ViewGroup parent, int viewType) {
        if ((viewType & ITEM_TYPE_OF_HEADER) == ITEM_TYPE_OF_HEADER) {
            ItemView itemViewByType = getItemViewByType(viewType);
            Log.d(TAG, "createViewHolder: viewType-->" + viewType + "itemViewByType:-->" + itemViewByType);
            if (itemViewByType == null ) {
                return upperItemTypeZone.createViewHolder(parent, viewType);
                //throw new IllegalArgumentException("viewType 不匹配: header类型的viewType, 在header中找不到对应的ItemView");
            }
            return itemViewByType.createViewHolder(parent, viewType);
        }
        return upperItemTypeZone.createViewHolder(parent, viewType);
    }

    @Override
    public void setSupportItemTypeZone(ItemTypeZone typeZone) {
        upperItemTypeZone = typeZone;
    }

    @Override
    public int size(){
        return length() + (upperItemTypeZone == null ? 0 : upperItemTypeZone.size());
    }

    private int length() {
        return headers == null ? 0: headers.size();
    }

    private ItemView getItemViewByType(int viewType) {
        for (ItemView header : headers) {
            if (header.ItemViewType() == viewType) {
                return header;
            }
        }
        return null;
    }
}
