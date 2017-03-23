package com.design.reader.module.main.fragment.shelf.purchased;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.design.reader.R;
import com.design.reader.adapter.BookListAdapter;
import com.design.reader.base.BaseFragment;
import com.design.reader.entity.BookInfo;
import com.design.reader.tools.BookDividerDecoration;
import com.lhh.ptrrv.library.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class PurchasedFragment extends BaseFragment<PurchasedView, PurchasedPresenter> implements PurchasedView {

    @BindView(R.id.recycler_purchased)
    PullToRefreshRecyclerView pullToRefreshRecyclerView;

    @Override
    public void initViews(View view) {
        BookListAdapter bookListAdapter = new BookListAdapter();
        List<BookInfo> infos = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            BookInfo bookInfo = new BookInfo();
            bookInfo.setRes(R.mipmap.setting2);
            bookInfo.setName("购买小说" + i);
            bookInfo.setPrice(40 + i);
            infos.add(bookInfo);
        }
        bookListAdapter.setInfos(infos);
        pullToRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        pullToRefreshRecyclerView.getRecyclerView().addItemDecoration(new BookDividerDecoration(getActivity()));
        pullToRefreshRecyclerView.setAdapter(bookListAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_purchased;
    }

    @Override
    public PurchasedPresenter createPresenter() {
        return new PurchasedPresenter();
    }
}
