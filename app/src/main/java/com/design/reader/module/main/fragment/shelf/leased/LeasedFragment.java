package com.design.reader.module.main.fragment.shelf.leased;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.design.reader.R;
import com.design.reader.adapter.BookListAdapter;
import com.design.reader.base.BaseFragment;
import com.design.reader.entity.BookInfo;
import com.design.reader.tools.BookDividerDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class LeasedFragment extends BaseFragment<LeasedView, LeasedPresenter> implements LeasedView {


    @BindView(R.id.recycler_leased)
    XRecyclerView mRecyclerView;


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
        mRecyclerView.addItemDecoration(new BookDividerDecoration(getActivity()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(bookListAdapter);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                Observable.timer(3, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        infos.clear();
                        for (int i = 0; i < COUNT + 20; i++) {
                            BookInfo bookInfo = new BookInfo();
                            bookInfo.setRes(R.mipmap.test);
                            bookInfo.setName("租赁小说" + i);
                            bookInfo.setPrice(COUNT + i);
                            bookInfo.setDescription(getResources().getString(R.string.test_string));
                            infos.add(bookInfo);
                        }
                        COUNT += 20;
                        bookListAdapter.setInfos(infos);
                        mRecyclerView.refreshComplete();
                    }
                });
            }

            @Override
            public void onLoadMore() {
                Observable.timer(3, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        for (int i = COUNT; i < COUNT + 20; i++) {
                            BookInfo bookInfo = new BookInfo();
                            bookInfo.setRes(R.mipmap.test);
                            bookInfo.setName("租赁小说" + i);
                            bookInfo.setPrice(COUNT + i);
                            bookInfo.setDescription(getResources().getString(R.string.test_string));
                            infos.add(bookInfo);
                        }
                        COUNT += 20;
                        bookListAdapter.setInfos(infos);
                        mRecyclerView.loadMoreComplete();
                    }
                });
            }
        });
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
