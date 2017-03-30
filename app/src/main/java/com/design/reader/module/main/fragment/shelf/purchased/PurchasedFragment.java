package com.design.reader.module.main.fragment.shelf.purchased;

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
import com.lhh.ptrrv.library.PullToRefreshRecyclerView;

import java.io.File;
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
            bookInfo.setRes(R.mipmap.test);
            bookInfo.setName("购买小说" + i);
            bookInfo.setPrice(40 + i);
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
