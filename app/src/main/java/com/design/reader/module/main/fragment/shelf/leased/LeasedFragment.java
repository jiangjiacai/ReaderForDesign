package com.design.reader.module.main.fragment.shelf.leased;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.design.reader.R;
import com.design.reader.adapter.BookListAdapter;
import com.design.reader.base.BaseFragment;
import com.design.reader.entity.BookInfo;
import com.design.reader.tools.BookDividerDecoration;
import com.lhh.ptrrv.library.PullToRefreshRecyclerView;
import com.lhh.ptrrv.library.footer.loadmore.BaseLoadMoreView;

import java.io.File;
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
            bookInfo.setRes(R.mipmap.test);
            bookInfo.setName("租赁小说" + i);
            bookInfo.setPrice(COUNT + i);
            bookInfo.setDescription(getResources().getString(R.string.test_string));
            infos.add(bookInfo);
        }
        bookListAdapter.setInfos(infos);
        bookListAdapter.setOnItemClickListener(new BookListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                String str = Environment.getExternalStorageDirectory().getPath() + "/test.txt";
                File file = new File(str);
                if (file.exists()) {
                    Toast.makeText(getActivity(), "书籍存在", Toast.LENGTH_SHORT).show();
                    Uri path = Uri.fromFile(file);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(path, "text/plain");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    getActivity().startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "书籍不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });
        pullToRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        pullToRefreshRecyclerView.getRecyclerView().addItemDecoration(new BookDividerDecoration(getActivity()));
        pullToRefreshRecyclerView.removeHeader();
        pullToRefreshRecyclerView.setSwipeEnable(true);
        pullToRefreshRecyclerView.setLoadMoreCount(120);
//        pullToRefreshRecyclerView.addHeaderView(View.inflate(getActivity(), R.layout.book_list_item, null));
        BaseLoadMoreView baseLoadMoreView = new BaseLoadMoreView(getActivity(), pullToRefreshRecyclerView.getRecyclerView());
        baseLoadMoreView.setLoadmoreString("加载中...");
        baseLoadMoreView.setLoadMorePadding(100);
        pullToRefreshRecyclerView.setLoadMoreFooter(baseLoadMoreView);
        pullToRefreshRecyclerView.setPagingableListener(new PullToRefreshRecyclerView.PagingableListener() {
            @Override
            public void onLoadMoreItems() {
                final List<BookInfo> infos = new ArrayList<>();
                for (int i = 0; i < 120; i++) {
                    BookInfo bookInfo = new BookInfo();
                    bookInfo.setRes(R.mipmap.test);
                    bookInfo.setName("租赁小说" + i);
                    bookInfo.setPrice(120 + i);
                    bookInfo.setDescription(getResources().getString(R.string.test_string));
                    infos.add(bookInfo);
                }
                Observable.timer(3, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
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
                    bookInfo.setRes(R.mipmap.test);
                    bookInfo.setName("租赁小说" + i);
                    bookInfo.setPrice(80 + i);
                    bookInfo.setDescription(getResources().getString(R.string.test_string));
                    infos.add(bookInfo);
                }
                Observable.timer(3, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
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
