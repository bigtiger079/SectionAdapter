package com.tiger.sectionadapter;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.tiger.sectionadapter.itemtype.FooterItemTypeZone;
import com.tiger.sectionadapter.itemtype.HeaderItemTypeZone;
import com.tiger.sectionadapter.itemtype.ItemTypeZone;
import com.tiger.sectionadapter.itemtype.NormalItemTypeZone;


/**
 * -----------------------------------------------------------
 * 版 权 ： BigTiger 版权所有 (c) 2015
 * 作 者 : BigTiger
 * 版 本 ： 1.0
 * 创建日期 ：2015/12/9
 * 描 述 ：可以添加任意数量头部跟脚部视图的适配器
 * <p>
 * -------------------------------------------------------------
 **/
public class HeaderFooterAdapter extends RecyclerView.Adapter<DataViewHolder> {
    private static final String TAG = "HeaderFooterAdapter";
    private HeaderItemTypeZone header;   //管理头部视图集群
    private FooterItemTypeZone footer;   //管理脚部视图集群
    private NormalItemTypeZone normal;   //中间普通视图集群, 用内部仍然为adapter
    public HeaderFooterAdapter(){
        header = new HeaderItemTypeZone();
        footer = new FooterItemTypeZone();
        normal = new NormalItemTypeZone();
        header.setSupportItemTypeZone(normal);
        normal.setSupportItemTypeZone(footer);
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return header.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        header.bindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return header.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < 0) {
            return -1;
        }
        int itemTypeByPosition = header.getItemTypeByPosition(position);
        return itemTypeByPosition;
    }

    /**
     * 根据位置获取视图的类型区域(见ItemTypeZone)
     * @param position  视图的位置
     * @return  视图的类型区域
     */
    public int getItemZoneTypeByPosition(int position) {
        return header.getItemZoneTypeByPosition(position);
    }

    public void addHeader(@NonNull ItemView itemView){
        addItemViewHeaderOrFooter(itemView, true);
    }

    /**
     * 添加itemView到header或者footer中
     * @param itemView 要添加的ItemView
     * @param isHeader  true:添加到header中, false添加到footer中
     */
    public void addItemViewHeaderOrFooter(@NonNull ItemView itemView, boolean isHeader){

        if (itemView == null) {
            throw new NullPointerException("要添加的"+(isHeader ? "header" : "footer") + "的itemView 不能为空!");
        }
        if (!invalidateViewType(itemView.ItemViewType(), isHeader)) {
            throw new IllegalArgumentException("要添加的itemView的viewType不匹配, 请参考"+(isHeader ? "header" :"footer")+"的viewType的匹配规则");
        }
        if (isHeader) {
            header.headers.add(itemView);
        } else {
            footer.footers.add(itemView);
        }
        notifyDataSetChanged();
    }

    // @Nullable BaseRecyclerAdapter adapter
    public void setAdapter(@Nullable RecyclerView.Adapter adapter){
        this.normal.adapter = adapter;
        notifyDataSetChanged();
    }



    @CheckResult
    public boolean removeItemView(ItemView itemView){
        if (header.headers != null) {
            if (header.headers.contains(itemView)) {
                return header.headers.remove(itemView);
            }
        } else if (footer.footers != null){
            if (footer.footers.contains(itemView)) {
                return footer.footers.remove(itemView);
            }
        }
        return false;
    }

    /**
     * 校验viewType 是否符合header或者footer的规则
     * @param viewType 要校验的viewType
     * @param isHeader 是否为header
     * @return  校验结果, true说明符合规则
     */
    private boolean invalidateViewType(int viewType, boolean isHeader) {
        int flag = ItemTypeZone.ITEM_TYPE_OF_HEADER | ItemTypeZone.ITEM_TYPE_OF_FOOTER | ItemTypeZone.ITEM_TYPE_OF_NORMAL;
        if (isHeader) {
            return (viewType & flag & ItemTypeZone.ITEM_TYPE_OF_HEADER) == ItemTypeZone.ITEM_TYPE_OF_HEADER;
        } else {
            Log.i(TAG, Integer.toBinaryString(viewType & flag & ItemTypeZone.ITEM_TYPE_OF_FOOTER));
            return (viewType & flag & ItemTypeZone.ITEM_TYPE_OF_FOOTER) == ItemTypeZone.ITEM_TYPE_OF_FOOTER;
        }
    }

    @Override
    public void onViewRecycled(DataViewHolder holder) {
        if (holder.getItemViewType() != ItemTypeZone.ITEM_TYPE_OF_HEADER && holder.getItemViewType() != ItemTypeZone.ITEM_TYPE_OF_FOOTER) {
            normal.adapter.onViewRecycled(holder);
        }
    }

    @Override
    public void onViewAttachedToWindow(DataViewHolder holder) {
        if (holder.getItemViewType() != ItemTypeZone.ITEM_TYPE_OF_HEADER && holder.getItemViewType() != ItemTypeZone.ITEM_TYPE_OF_FOOTER) {
            normal.adapter.onViewAttachedToWindow(holder);
        }
    }

    @Override
    public void onViewDetachedFromWindow(DataViewHolder holder) {
        if (holder.getItemViewType() != ItemTypeZone.ITEM_TYPE_OF_HEADER && holder.getItemViewType() != ItemTypeZone.ITEM_TYPE_OF_FOOTER) {
            normal.adapter.onViewDetachedFromWindow(holder);
        }
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
        if (normal != null && normal.adapter != null) {
            normal.adapter.registerAdapterDataObserver(observer);
        }
    }

    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.unregisterAdapterDataObserver(observer);
        if (normal != null && normal.adapter != null) {
            normal.adapter.unregisterAdapterDataObserver(observer);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        if (normal != null && normal.adapter != null) {
            normal.adapter.onAttachedToRecyclerView(recyclerView);
        }

    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        if (normal != null && normal.adapter != null) {
            normal.adapter.onDetachedFromRecyclerView(recyclerView);
        }
    }

}
