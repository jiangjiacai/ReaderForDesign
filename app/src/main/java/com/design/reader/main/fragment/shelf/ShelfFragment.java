package com.design.reader.main.fragment.shelf;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.design.reader.R;
import com.design.reader.adapter.BookListAdapter;
import com.design.reader.base.BaseFragment;
import com.design.reader.entity.BookInfo;
import com.design.reader.tools.BookDividerDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ShelfFragment extends BaseFragment<ShelfView, ShelfPresenter> implements ShelfView {


    @BindView(R.id.search_imageView)
    ImageView searchImageView;
    @BindView(R.id.book_recycler)
    RecyclerView bookRecycler;

    @Override
    public void initViews(View view) {
        BookListAdapter bookListAdapter = new BookListAdapter();
        List<BookInfo> infos = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            BookInfo bookInfo = new BookInfo();
            bookInfo.setRes(R.mipmap.setting2);
            bookInfo.setName("小说" + i);
            bookInfo.setPrice(40 + i);
            infos.add(bookInfo);
        }
        bookListAdapter.setInfos(infos);
        bookRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        bookRecycler.addItemDecoration(new BookDividerDecoration(getActivity()));
        bookRecycler.setAdapter(bookListAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shelf;
    }

    @Override
    public ShelfPresenter createPresenter() {
        return new ShelfPresenter();
    }
}
