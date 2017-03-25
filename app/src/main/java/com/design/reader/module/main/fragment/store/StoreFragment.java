package com.design.reader.module.main.fragment.store;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.design.reader.R;
import com.design.reader.adapter.BookListAdapter;
import com.design.reader.base.BaseFragment;
import com.design.reader.entity.BookInfo;
import com.design.reader.module.read.ReadActivity;
import com.design.reader.tools.BookDividerDecoration;
import com.lhh.ptrrv.library.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class StoreFragment extends BaseFragment<StoreView, StorePresenter> implements StoreView {

    @BindView(R.id.recycler_store)
    PullToRefreshRecyclerView pullToRefreshRecyclerView;

    @Override
    public void initViews(View view) {
        final BookListAdapter bookListAdapter = new BookListAdapter();
        final List<BookInfo> infos = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            BookInfo bookInfo = new BookInfo();
            bookInfo.setRes(R.mipmap.setting2);
            bookInfo.setName("租赁小说" + i);
            bookInfo.setPrice(40 + i);
            infos.add(bookInfo);
        }
        bookListAdapter.setInfos(infos);
        bookListAdapter.setOnItemClickListener(new BookListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getActivity(), ReadActivity.class);
                getActivity().startActivity(intent);
            }
        });
        pullToRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        pullToRefreshRecyclerView.getRecyclerView().addItemDecoration(new BookDividerDecoration(getActivity()));
        pullToRefreshRecyclerView.removeHeader();
        pullToRefreshRecyclerView.setSwipeEnable(true);
        pullToRefreshRecyclerView.setAdapter(bookListAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_store;
    }

    @Override
    public StorePresenter createPresenter() {
        return new StorePresenter();
    }
}
