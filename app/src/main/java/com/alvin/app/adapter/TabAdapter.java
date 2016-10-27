package com.alvin.app.adapter;

import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;

/**
 * @Title TabAdapter
 * @Description:
 * @Author: alvin
 * @Date: 2016/10/26.16:08
 * @E-mail: 49467306@qq.com
 */
public abstract class TabAdapter {
    private DataSetObserver mObserver;

    /**
     * 注册数据变化
     * @param observer
     */
    public void registerObserver(DataSetObserver observer) {
        mObserver = observer;
    }

    /**
     * 取消注册
     */
    public void unregisterObserver() {
        mObserver = null;
    }

    public void notifyDataSetChanged() {
        if (mObserver != null) mObserver.onChanged();
    }

    public void notifyDataSetInvalidate() {
        if (mObserver != null) mObserver.onInvalidated();
    }

    public abstract int getItemCount();

    public abstract Drawable getDrawable(int position);

    public abstract CharSequence getText(int position);
}
