package com.tiger.sectionadapter.itemtype;

import android.support.annotation.IntDef;
import android.view.ViewGroup;

import com.tiger.sectionadapter.DataViewHolder;

/**
 * -----------------------------------------------------------
 * 版 权 ： BigTiger 版权所有 (c) 2015
 * 作 者 : BigTiger
 * 版 本 ： 1.0
 * 创建日期 ：2015/12/9
 * 描 述 ：
 * <p>
 * -------------------------------------------------------------
 **/
public interface ItemTypeZone {
    //既定的三个视图区域
    static final int ITEM_TYPE_OF_HEADER = 0x1 << 31;       //头部区域
    static final int ITEM_TYPE_OF_NORMAL = 0x1 << 29;       //普通视图区域
    static final int ITEM_TYPE_OF_FOOTER = 0x1 << 30;       //脚部区域
    @IntDef({ITEM_TYPE_OF_HEADER, ITEM_TYPE_OF_NORMAL, ITEM_TYPE_OF_FOOTER})
    @interface ItemType{}

    /**
     * 根据位置判断当前位置View是属于哪个区域(头部区域, 普通数据区域, 脚部区域)
     * @param position 视图的位置
     * @return @ItemType
     */
    public @ItemType int getItemZoneTypeByPosition(int position);

    /**
     * 绑定对应位置的ViewHolder
     * @param holder    要绑定的ViewHolder
     * @param position  viewHolder对应的位置
     */
    public void bindViewHolder(DataViewHolder holder, int position);

    /**
     * 获取给定位置的视图的viewType
     * @param position  要查找的位置
     * @return  viewType
     */
    public int getItemTypeByPosition(int position);

    /**
     * 根据viewType创建相应的ViewHolder
     * @param parent    RecyclerView
     * @param viewType  视图类型
     * @return  创建好的ViewHolder
     */
    public DataViewHolder createViewHolder(ViewGroup parent, int viewType);

    /**
     * 设置上级视图区域
     * @param typeZone
     */
    public void setSupportItemTypeZone(ItemTypeZone typeZone);


    public int size();
}
