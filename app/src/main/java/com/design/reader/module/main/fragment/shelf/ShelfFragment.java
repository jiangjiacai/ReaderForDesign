package com.design.reader.module.main.fragment.shelf;

import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.design.reader.R;
import com.design.reader.adapter.BookListAdapter;
import com.design.reader.adapter.ShelfFragmentPagerAdapter;
import com.design.reader.base.BaseFragment;
import com.design.reader.entity.BookInfo;
import com.design.reader.tools.BookDividerDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShelfFragment extends BaseFragment<ShelfView, ShelfPresenter> implements ShelfView {

    public static final int LEASED = 1;
    public static final int PURCHASED = 0;

    @BindView(R.id.search_imageView)
    ImageView searchImageView;
    @BindView(R.id.book_recycler)
    RecyclerView bookRecycler;
    @BindView(R.id.shelf_pager_strip)
    PagerTabStrip shelfPagerStrip;
    @BindView(R.id.shelf_viewpager)
    ViewPager shelfViewpager;

    @Override
    public void initViews(View view) {

        ShelfFragmentPagerAdapter adapter = new ShelfFragmentPagerAdapter(getChildFragmentManager());
        shelfViewpager.setAdapter(adapter);

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
