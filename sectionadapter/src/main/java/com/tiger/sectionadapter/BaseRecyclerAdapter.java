package com.tiger.sectionadapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * -----------------------------------------------------------
 * 版 权 ： BigTiger 版权所有 (c) 2015
 * 作 者 : BigTiger
 * 版 本 ： 1.0
 * 创建日期 ：2015/12/3
 * 描 述 ：继承RecyclerView.Adapter, 用SortedList管理数据, 强制ViewHolder为DataViewHolder的子类
 * <p>
 * -------------------------------------------------------------
 **/
public abstract class BaseRecyclerAdapter <DataType, VH extends DataViewHolder> extends RecyclerView.Adapter<VH> {
    protected OnRecyclerItemClickListener listener = null;
    //protected SortedList<DataType> mData = new SortedList<DataType>(getDataClass(), getSortedListAdapterCallback());
    protected List<DataType> mData = new ArrayList<DataType>();

    //-------------------- begin 数据的CURD ------------------

    public void add(DataType data) {
        mData.add(data);
        notifyItemInserted(mData.size() - 1);
    }

    public void clear() {
        int size = mData.size();
        mData.clear();
        notifyItemRangeChanged(0, size);
    }

    public int indexOf(DataType data) {
        return mData.indexOf(data);
    }

    /*public void recalculatePositionOfItemAt(int index) {
        //mData.recalculatePositionOfItemAt(index);
    }*/

    public void remove(DataType data) {
        if (mData.contains(data)) {
            int index = mData.indexOf(data);
            mData.remove(data);
            notifyItemRemoved(index);
        }
    }

    public void updateItemAt(int index, DataType data) {
        //mData.updateItemAt(index, data);
        mData.set(index, data);
        notifyItemChanged(index);
    }

    public DataType removeItemAt(int index, int adapterPosition) {
        //DataType dataType = mData.removeItemAt(index);
        DataType dataType = mData.remove(index);
        if (index != adapterPosition) { //这里是为了更新ViewHolder里面的index数据
            notifyDataSetChanged();
        }
        return  dataType;
    }

    public DataType get(int index) {
        return mData.get(index);
    }

    /**
     * 把新数据添加到原数据集的后面
     * @param data 要添加的数据集
     */
    public void addAll(List<DataType> data){
        //mData.beginBatchedUpdates();
        int indexBegin = mData.size();
        try {
            for (DataType dataType : data) {
                mData.add(dataType);
            }
        } finally {
            //mData.endBatchedUpdates();
            notifyItemRangeInserted(indexBegin - 1, data.size());
        }
    }

    /**
     * 重新设置数据集, 先把原数据清空, 再将数据添加到集合中
     * @param data  要重新添加的数据
     */
    public void setData(List<DataType> data) {
        mData.clear();
        addAll(data);
    }

    public DataType getLastItem(){
        return mData == null ? null: mData.get(mData.size() - 1);
    }

    //---------- end 数据的CURD ----------

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    /**
     * 子类可以覆盖此方法来改变默认的SortedListAdapterCallback, 如果添加的数据不需要重新排序的话不建议替换
     * @return 默认内部不排序的SortedListAdapterCallback
     */
    protected SortedListAdapterCallback<DataType> getSortedListAdapterCallback(){
        return new SortedListAdapterCallback<DataType>(this) {
            @Override
            public int compare(DataType o1, DataType o2) {
                return -1;
            }

            @Override
            public boolean areContentsTheSame(DataType oldItem, DataType newItem) {
                return false;
            }

            @Override
            public boolean areItemsTheSame(DataType item1, DataType item2) {
                return false;
            }
        };
    }

    public abstract Class<DataType> getDataClass();

    public void setOnItemClickListener(OnRecyclerItemClickListener listener) {
        this.listener = listener;
    }

}
