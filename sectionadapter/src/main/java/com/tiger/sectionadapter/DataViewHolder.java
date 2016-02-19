package com.tiger.sectionadapter;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tiger.sectionadapter.model.IViewHolderModel;


/**
 * -----------------------------------------------------------
 * 版 权 ： BigTiger 版权所有 (c) 2015
 * 作 者 : BigTiger
 * 版 本 ： 1.0
 * 创建日期 ：2015/12/10
 * 描 述 ：继承RecyclerView.ViewHolder, 同时实现视图模型接口, 用于给当前视图绑定对应的数据
 * <p/>
 * -------------------------------------------------------------
 **/
public abstract class DataViewHolder<D> extends RecyclerView.ViewHolder implements IViewHolderModel<D> {

    public DataViewHolder(View itemView) {
        super(itemView);
    }

    protected <V extends View>V findView(@IdRes int id){
        return (V) itemView.findViewById(id);
    }

    protected <V extends View>V findView(View parent, @IdRes int id){
        return (V) parent.findViewById(id);
    }

}
