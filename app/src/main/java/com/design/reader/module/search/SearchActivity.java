package com.design.reader.module.search;

import com.design.reader.R;
import com.design.reader.base.BaseActivity;

public class SearchActivity extends BaseActivity<SearchView,SearchPresenter> implements SearchView {


    @Override
    public void initViews() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
    }
}
