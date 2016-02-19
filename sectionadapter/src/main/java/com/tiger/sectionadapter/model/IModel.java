package com.tiger.sectionadapter.model;

import android.support.annotation.NonNull;

/**
 * -----------------------------------------------------------
 * 版 权 ： BigTiger 版权所有 (c) 2015
 * 作 者 : BigTiger
 * 版 本 ： 1.0
 * 创建日期 ：2015/11/24
 * 描 述 ：
 * <p>
 * -------------------------------------------------------------
 **/
public interface IModel<View, Data> {

    void setData(@NonNull Data data);
}
