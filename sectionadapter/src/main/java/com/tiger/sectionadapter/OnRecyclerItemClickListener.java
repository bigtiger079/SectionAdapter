package com.tiger.sectionadapter;

import android.view.View;

/**
 * -----------------------------------------------------------
 * 版 权 ： BigTiger 版权所有 (c) 2015
 * 作 者 : BigTiger
 * 版 本 ： 1.0
 * 创建日期 ：2015/12/14
 * 描 述 ：RecyclerView的Item点击事件
 * <p>
 * -------------------------------------------------------------
 **/
public interface OnRecyclerItemClickListener{

    /**
     * 当ItemView被点击了
     * @param view  触发点击的View
     * @param dataPosition  Item在数据集中对应的位置
     * @param adapterPosition   Item在Adapter中的位置
     */
    void onItemClicked(View view, int dataPosition, int adapterPosition);
}
