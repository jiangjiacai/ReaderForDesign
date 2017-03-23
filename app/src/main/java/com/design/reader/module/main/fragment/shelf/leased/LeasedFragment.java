package com.design.reader.module.main.fragment.shelf.leased;

import android.support.v4.widget.SwipeRefreshLayout;
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
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class LeasedFragment extends BaseFragment<LeasedView, LeasedPresenter> implements LeasedView {

    @BindView(R.id.recycler_leased)
    PullToRefreshRecyclerView pullToRefreshRecyclerView;

    private int COUNT = 40;

    @Override
    public void initViews(View view) {
        final BookListAdapter bookListAdapter = new BookListAdapter();
        final List<BookInfo> infos = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            BookInfo bookInfo = new BookInfo();
            bookInfo.setRes(R.mipmap.setting2);
            bookInfo.setName("租赁小说" + i);
            bookInfo.setPrice(COUNT + i);
            infos.add(bookInfo);
        }
        bookListAdapter.setInfos(infos);
        pullToRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        pullToRefreshRecyclerView.getRecyclerView().addItemDecoration(new BookDividerDecoration(getActivity()));
        pullToRefreshRecyclerView.removeHeader();
        pullToRefreshRecyclerView.setSwipeEnable(true);
//        pullToRefreshRecyclerView.addHeaderView(View.inflate(getActivity(), R.layout.book_list_item, null));
        pullToRefreshRecyclerView.setLoadmoreString("加载中...");
        pullToRefreshRecyclerView.setPagingableListener(new PullToRefreshRecyclerView.PagingableListener() {
            @Override
            public void onLoadMoreItems() {
                final List<BookInfo> infos = new ArrayList<>();
                for (int i = 0; i < 120; i++) {
                    BookInfo bookInfo = new BookInfo();
                    bookInfo.setRes(R.mipmap.setting2);
                    bookInfo.setName("租赁小说" + i);
                    bookInfo.setPrice(120 + i);
                    infos.add(bookInfo);
                }
                Observable.timer(3, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        bookListAdapter.setInfos(infos);
                        bookListAdapter.notifyDataSetChanged();
                        pullToRefreshRecyclerView.setOnLoadMoreComplete();
                    }
                });
            }
        });
        pullToRefreshRecyclerView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                final List<BookInfo> infos = new ArrayList<>();
                for (int i = 0; i < 80; i++) {
                    BookInfo bookInfo = new BookInfo();
                    bookInfo.setRes(R.mipmap.setting2);
                    bookInfo.setName("租赁小说" + i);
                    bookInfo.setPrice(80 + i);
                    infos.add(bookInfo);
                }
                Observable.timer(3, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        bookListAdapter.setInfos(infos);
                        bookListAdapter.notifyDataSetChanged();
                        pullToRefreshRecyclerView.setOnRefreshComplete();
                    }
                });
            }
        });
        pullToRefreshRecyclerView.onFinishLoading(true, false);
        pullToRefreshRecyclerView.setAdapter(bookListAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_leased;
    }

    @Override
    public LeasedPresenter createPresenter() {
        return new LeasedPresenter();
    }
}
