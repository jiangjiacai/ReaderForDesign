package com.design.reader.module.main.fragment.store;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.design.reader.R;
import com.design.reader.adapter.BookListAdapter;
import com.design.reader.base.BaseFragment;
import com.design.reader.entity.BookInfo;
import com.design.reader.module.bookdetail.BookDetailActivity;
import com.design.reader.module.search.SearchActivity;
import com.design.reader.tools.BookDividerDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class StoreFragment extends BaseFragment<StoreView, StorePresenter> implements StoreView {

    @BindView(R.id.recycler_store)
    XRecyclerView mRecyclerView;

    @Override
    public void initViews(View view) {
        final BookListAdapter bookListAdapter = new BookListAdapter();
        final List<BookInfo> infos = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            BookInfo bookInfo = new BookInfo();
            bookInfo.setRes(R.mipmap.test);
            bookInfo.setName("租赁小说" + i);
            bookInfo.setPrice(40 + i);
            bookInfo.setDescription(getResources().getString(R.string.test_string));
            infos.add(bookInfo);
        }
        bookListAdapter.setInfos(infos);
        bookListAdapter.setOnItemClickListener(new BookListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position, BookInfo bookInfo) {
//                String str = Environment.getExternalStorageDirectory().getPath() + "/test.txt";
//                File file = new File(str);
//                if (file.exists()) {
//                    Toast.makeText(getActivity(), "书籍存在", Toast.LENGTH_SHORT).show();
//                    Uri path = Uri.fromFile(file);
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setDataAndType(path, "text/plain");
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    getActivity().startActivity(intent);
//                } else {
//                    Toast.makeText(getActivity(), "书籍不存在", Toast.LENGTH_SHORT).show();
//                }

                Intent intent = new Intent(getActivity(), BookDetailActivity.class);
                getActivity().startActivity(intent);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new BookDividerDecoration(getActivity()));
        mRecyclerView.setAdapter(bookListAdapter);
    }

    @OnClick({R.id.search_store})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_store:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
        }
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
