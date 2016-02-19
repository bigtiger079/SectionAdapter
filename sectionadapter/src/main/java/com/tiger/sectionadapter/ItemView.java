package com.tiger.sectionadapter;

import android.view.ViewGroup;

/**
 * -----------------------------------------------------------
 * 版 权 ： BigTiger 版权所有 (c) 2015
 * 作 者 : BigTiger
 * 版 本 ： 1.0
 * 创建日期 ：2015/12/9
 * 描 述 ：ItemView 管理单个数据跟视图的绑定.
 *          虽然取名为itemView, 但是他属于数据模型, 用于给视图提供相应的数据
 *          并且在特定是时间创建视图缓存, 绑定视图缓存
 * <p>
 * -------------------------------------------------------------
 **/
public interface ItemView<T> {

    /**
     * 获取视图数据
     * @return
     */
    public T getData();

    /**
     * 设置视图对应的数据
     * @param data
     */
    public void setData(T data);

    /**
     * 当前视图的类型
     * @return
     */
    public int ItemViewType();

    /**
     * 绑定视图数据
     * @param holder 要绑定的视图的ViewHolder
     * @param position  当前视图在adapter中的位置
     * @param <VH>
     */
    public<VH extends DataViewHolder<T>> void bindViewHolder(VH holder, int position);

    /**
     * 创建视图缓存
     * @param parent    RecyclerView
     * @param viewType  视图类型
     * @param <VH>  视图缓存 ViewHolder
     * @return  DataViewHolder的子类
     */
    public <VH extends DataViewHolder<T>> VH createViewHolder(ViewGroup parent, int viewType);
}
