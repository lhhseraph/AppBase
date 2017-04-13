package com.base.base.adapter.helper;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/7/21.
 */
public interface DataHelper<T> {

    boolean setData(List<T> list);

    boolean addAll(List<T> list);

    boolean addAll(int position, List<T> list);

    void add(T data);

    void add(int position, T data);

    void clear();

    boolean contains(T data);

    T getData(int index);

    void modify(T oldData, T newData);

    void modify(int index, T newData);

    boolean remove(T data);

    void remove(int index);

}
