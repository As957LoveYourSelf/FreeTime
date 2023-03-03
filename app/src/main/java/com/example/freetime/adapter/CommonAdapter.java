package com.example.freetime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;
    public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }
    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = getViewHolder(position, convertView, parent);
        convert(viewHolder, getItem(position));
        //getItem(position)的类型就是T，这句话在子类中的具体实现就是给具体的控件初始化
        //并赋值，初始化赋值控件时需要viewHolder和具体的数据Java bean，在这里抽象出来就是类型T
        return viewHolder.getConvertView();
    }

    public abstract void convert(ViewHolder helper, T item);

    private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId, position);
    }
}
