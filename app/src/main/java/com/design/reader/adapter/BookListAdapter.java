package com.design.reader.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.design.reader.R;
import com.design.reader.base.MyApplication;
import com.design.reader.entity.BookInfo;
import com.design.reader.module.read.ReadActivity;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;

    private List<BookInfo> infos;

    private OnItemClickListener onItemClickListener;

    public BookListAdapter() {
        mLayoutInflater = LayoutInflater.from(MyApplication.getContext());
    }

    public void setInfos(List<BookInfo> infos) {
        this.infos = infos;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookViewHolder(mLayoutInflater.inflate(R.layout.book_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof BookViewHolder) {
            if (infos != null && !infos.isEmpty()) {
                final BookInfo bookInfo = infos.get(position);
                ((BookViewHolder) holder).imageView.setImageResource(bookInfo.getRes());
                ((BookViewHolder) holder).bookName.setText(bookInfo.getName());
                ((BookViewHolder) holder).bookPrice.setText(bookInfo.getDescription());
                if (onItemClickListener != null) {
                    ((BookViewHolder) holder).bookItem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onItemClickListener.onItemClick(view, position, bookInfo);
                        }
                    });
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (infos != null && !infos.isEmpty()) {
            return infos.size();
        }
        return 0;
    }

    private static class BookViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView bookName;
        TextView bookPrice;
        LinearLayout bookItem;

        public BookViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.book_image);
            bookName = (TextView) itemView.findViewById(R.id.book_name);
            bookPrice = (TextView) itemView.findViewById(R.id.book_price);
            bookItem = (LinearLayout) itemView.findViewById(R.id.book_item);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position, BookInfo bookInfo);
    }
}
