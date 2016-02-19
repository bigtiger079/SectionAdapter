package com.tiger.sectionadapter.itemtype;

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
 * 描 述 ：脚部视图区域的实现
 * <p>
 * -------------------------------------------------------------
 **/
public class FooterItemTypeZone implements ItemTypeZone {
    public List<ItemView> footers = new ArrayList<>();
    @Override
    public int getItemZoneTypeByPosition(int position) {
        if (footers == null || footers.size() < position ) {
            throw new IllegalArgumentException(" 要查找的位置已经超出了footer的范围 ");
        }
        return ITEM_TYPE_OF_FOOTER;
    }

    @Override
    public void bindViewHolder(DataViewHolder holder, int position) {
        checkoutSize(position);
        footers.get(position).bindViewHolder(holder, position);
    }

    @Override
    public int getItemTypeByPosition(int position) {
        checkoutSize(position);
        return footers.get(position).ItemViewType();
    }

    @Override
    public DataViewHolder createViewHolder(ViewGroup parent, int viewType) {
        ItemView itemViewByType = getItemViewByType(viewType);
        if (itemViewByType == null) {
            throw new IllegalArgumentException("viewType 错误! 要查找的viewType 不属于 footer类型的ItemView");
        }
        return itemViewByType.createViewHolder(parent, viewType);
    }

    @Override
    public void setSupportItemTypeZone(ItemTypeZone typeZone) {}

    @Override
    public int size(){
        return footers == null ? 0 : footers.size();
    }

    private void checkoutSize(int position){
        if (position >= size()) {
            throw new IllegalArgumentException("要获取的ItemView的位置超出了footer的范围");
        }
    }

    private ItemView getItemViewByType(int viewType) {
        for (ItemView footer : footers) {
            if (footer.ItemViewType() == viewType) {
                return footer;
            }
        }
        return null;
    }
}
