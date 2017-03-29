package com.design.reader.module.search;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.design.reader.R;
import com.design.reader.adapter.BookListAdapter;
import com.design.reader.base.BaseActivity;
import com.design.reader.entity.BookInfo;
import com.design.reader.tools.BookDividerDecoration;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseActivity<SearchView, SearchPresenter> implements SearchView, MaterialSearchBar.OnSearchActionListener {


    @BindView(R.id.searchBar)
    MaterialSearchBar searchBar;
    @BindView(R.id.search_recycler)
    RecyclerView recyclerView;

    private BookListAdapter bookListAdapter;

    @Override
    public void initViews() {
        if (!searchBar.isSearchEnabled()) {
            searchBar.enableSearch();
        }
        searchBar.setOnSearchActionListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookListAdapter = new BookListAdapter();
        recyclerView.addItemDecoration(new BookDividerDecoration(this));
        recyclerView.setAdapter(bookListAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {
        if (!enabled) {
            finish();
        }
    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
//        startSearch(text.toString(), true, null, true);
        List<BookInfo> infos = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            BookInfo bookInfo = new BookInfo();
            bookInfo.setRes(R.mipmap.setting2);
            bookInfo.setName("书" + i);
            bookInfo.setPrice(i);
            infos.add(bookInfo);
        }
        bookListAdapter.setInfos(infos);
        bookListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onButtonClicked(int buttonCode) {
        switch (buttonCode) {
            case MaterialSearchBar.BUTTON_NAVIGATION:
                Toast.makeText(this, "BUTTON_NAVIGATION ", Toast.LENGTH_SHORT).show();
//                drawer.openDrawer(Gravity.LEFT);
                break;
            case MaterialSearchBar.BUTTON_SPEECH:
                Toast.makeText(this, "BUTTON_SPEECH ", Toast.LENGTH_SHORT).show();
//                openVoiceRecognizer();
        }
    }

}
