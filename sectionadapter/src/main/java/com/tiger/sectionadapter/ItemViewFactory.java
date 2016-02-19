package com.tiger.sectionadapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.tiger.sectionadapter.itemtype.DefaultItemView;
import com.tiger.sectionadapter.itemtype.ItemTypeZone;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * -----------------------------------------------------------
 * 版 权 ： BigTiger 版权所有 (c) 2015
 * 作 者 : BigTiger
 * 版 本 ： 1.0
 * 创建日期 ：2015/12/9
 * 描 述 ：用于创建IteView的工厂类
 * <p>
 * -------------------------------------------------------------
 **/
public class ItemViewFactory {
    private static final String TAG = "ItemViewFactory";
    private static int count = new Random().nextInt(100);

    //创建一个普通的ItemView
    public static<T> ItemView<T> createDefaultItemView(T data,
                                                              @LayoutRes final int layoutId,
                                                              final Class<? extends DataViewHolder<T>> holderClass) {
        return createDefaultItemView(data, layoutId, holderClass, ItemTypeZone.ITEM_TYPE_OF_NORMAL);
    }

    //创建一个header 的ItemView
    public static <T> ItemView<T> createDefaultHeaderItemView(T data,
                                                                     @LayoutRes final int layoutId,
                                                                     final Class<? extends DataViewHolder<T>> holderClass) {
        return createDefaultItemView(data, layoutId, holderClass, ItemTypeZone.ITEM_TYPE_OF_HEADER);
    }

    //创建一个 footer的ItemView
    public static <T> ItemView<T> createDefaultFooterItemView(T data,
                                                                     @LayoutRes final int layoutId,
                                                                     final Class<? extends DataViewHolder<T>> holderClass) {
        return createDefaultItemView(data, layoutId, holderClass, ItemTypeZone.ITEM_TYPE_OF_FOOTER);
    }

    //创建一个普通的ItemView
    public static<T> ItemView<T> createDefaultItemView(T data,
                                                              View view,
                                                              final Class<? extends DataViewHolder> holderClass) {
        return createDefaultItemView(data, view, holderClass, ItemTypeZone.ITEM_TYPE_OF_NORMAL);
    }

    //创建一个header 的ItemView
    public static <T> ItemView<T> createDefaultHeaderItemView(T data,
                                                                     View view,
                                                                     final Class<? extends DataViewHolder<T>> holderClass) {
        return createDefaultItemView(data, view, holderClass, ItemTypeZone.ITEM_TYPE_OF_HEADER);
    }

    //创建一个 footer的ItemView
    public static <T> ItemView<T> createDefaultFooterItemView(T data,
                                                                     View view,
                                                                     final Class<? extends DataViewHolder> holderClass) {
        return createDefaultItemView(data, view, holderClass, ItemTypeZone.ITEM_TYPE_OF_FOOTER);
    }

    /**
     * 创建默认的ItemView
     * @param data ItemView持有的数据类型
     * @param view
     * @param holderClass
     * @param <T>
     * @return
     */
    public static<T> DefaultItemView<T> createDefaultItemView(T data,
                                                              final View view,
                                                              final Class<? extends DataViewHolder> holderClass,
                                                              @ItemTypeZone.ItemType final int type) {
        return new DefaultItemView<T>(data, generateItemViewType(type)) {
            @Override
            public <VH extends DataViewHolder<T>> VH createViewHolder(ViewGroup parent, int viewType) {
                return getViewHolderByClass(view, holderClass);
            }
        };
    }

    /**
     * 创建默认的ItemView
     * @param data ItemView持有的数据类型
     * @param layoutId
     * @param holderClass
     * @param <T>
     * @return
     */
    public static<T> DefaultItemView<T> createDefaultItemView(T data,
                                                              @LayoutRes final int layoutId,
                                                              final Class<? extends DataViewHolder<T>> holderClass,
                                                              @ItemTypeZone.ItemType final int type) {
        return new DefaultItemView<T>(data, generateItemViewType(type)) {
            @Override
            public <VH extends DataViewHolder<T>> VH createViewHolder(ViewGroup parent, int viewType) {
                return getViewHolderByClass(layoutId, parent.getContext(), holderClass);
            }
        };
    }

    private static int generateItemViewType(int type) {
        switch (type) {
            case ItemTypeZone.ITEM_TYPE_OF_HEADER : {
                count += 1;
                Log.d(TAG, "generateItemViewType: --->" + (count + ItemTypeZone.ITEM_TYPE_OF_HEADER));

                return count | ItemTypeZone.ITEM_TYPE_OF_HEADER;
            }
            case ItemTypeZone.ITEM_TYPE_OF_FOOTER : {
                count += 1;
                Log.i(TAG, Integer.toBinaryString(count | ItemTypeZone.ITEM_TYPE_OF_FOOTER) + "   " + Integer.toBinaryString(count));
                return count | ItemTypeZone.ITEM_TYPE_OF_FOOTER;
            }
            default:
                return 0;
        }
    }

    private static <VH extends DataViewHolder> VH getViewHolderByClass(int layoutId, Context context, Class<? extends DataViewHolder> hClass) {
        return getViewHolderByClass(View.inflate(context, layoutId, null), hClass);
    }

    private static <VH extends DataViewHolder> VH getViewHolderByClass(View view, Class<? extends DataViewHolder> hClass) {
        try {
            Constructor<?>[] constructors = hClass.getConstructors();
            for (Constructor<?> constructor : constructors) {
                System.out.println("测试:" + constructor.getName() );
                for (Class<?> aClass : constructor.getParameterTypes()) {
                    System.out.println(aClass.getName());
                }
            }
            Constructor<? extends DataViewHolder> constructor = hClass.getConstructor(View.class);
            return (VH) constructor.newInstance(view);
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            Throwable t = e.getTargetException();   // 获取目标异常
            t.printStackTrace();
//            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }

    }
}
