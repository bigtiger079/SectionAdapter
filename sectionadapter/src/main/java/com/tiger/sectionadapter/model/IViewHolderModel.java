package com.tiger.sectionadapter.model;

/**
 * -----------------------------------------------------------
 * 版 权 ： BigTiger 版权所有 (c) 2015
 * 作 者 : BigTiger
 * 版 本 ： 1.0
 * 创建日期 ：2015/12/15
 * 描 述 ：用于给ViewHolder绑定数据的接口
 * <p>
 * -------------------------------------------------------------
 **/
public interface IViewHolderModel<D> {
    /**
     * ViewHolder绑定数据
     * @param data  要绑定的数据
     * @param position  当前数据在adapter中的位置
     */
    void setData(D data, int position);
}