package com.tiger.sectionadapter.itemtype;

import android.view.ViewGroup;

import com.tiger.sectionadapter.DataViewHolder;
import com.tiger.sectionadapter.ItemView;


/**
 * -----------------------------------------------------------
 * 版 权 ： BigTiger 版权所有 (c) 2015
 * 作 者 : BigTiger
 * 版 本 ： 1.0
 * 创建日期 ：2015/12/9
 * 描 述 ：默认的实现的ItemView(用于header和footer)
 * <p>
 * -------------------------------------------------------------
 **/
public abstract class DefaultItemView<T> implements ItemView<T> {
    private T data; //Item绑定的数据类型
    private int itemType = 0;   //视图类型 -- 如果是header或者是footer的话要修改成符合类型规则的数
    public DefaultItemView(T data) {
        this.data = data;
    }

    public DefaultItemView(T data, int itemType) {
        this.data = data;
        this.itemType = itemType;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public int ItemViewType() {
        return itemType;
    }

    @Override
    public <VH extends DataViewHolder<T>> void bindViewHolder(VH holder, int position) {
        holder.setData(data, position);
    }

    @Override
    public abstract <VH extends DataViewHolder<T>> VH createViewHolder(ViewGroup parent, int viewType);
}
