package com.example.freetime.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.freetime.R;

import java.util.List;

/**
 * 自定义 RecycleView 相关适配器、数据、Holder布局的 Demo

 */
public class CustomRecycleViewAdapter extends RecyclerView.Adapter<CustomRecycleViewAdapter.ViewHolder> {

    AdapterView.OnItemClickListener onItemClickListener;

    public AdapterView.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public CustomRecycleViewAdapter setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public CustomRecycleViewAdapter(List<Data> mDataList) {
        this.mDataList = mDataList;
    }

    private List<Data> mDataList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_custom_recycleview, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Data data = mDataList.get(position);
        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) onItemClickListener.onItemClick(null, v, holder.getAdapterPosition(),0);
            }
        });
        holder.getTxtItem().setText(data.getText());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class Data {

        private String text;

        public Data(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public Data setText(String text) {
            this.text = text;
            return this;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtItem;
        View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            txtItem = itemView.findViewById(R.id.txt_item);
        }

        public View getItemView() {
            return itemView;
        }

        public TextView getTxtItem() {
            return txtItem;
        }
    }
}
