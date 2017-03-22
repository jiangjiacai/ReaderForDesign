package com.design.reader.module.bookdetail;

import com.design.reader.R;
import com.design.reader.base.BaseActivity;

public class BookDetailActivity extends BaseActivity<BookDetailView, BookDetailPresenter> implements BookDetailView {


    @Override
    public void initViews() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_book_detail;
    }

    @Override
    protected BookDetailPresenter createPresenter() {
        return new BookDetailPresenter();
    }
}
