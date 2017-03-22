package com.design.reader.module.bookdetail;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.design.reader.R;
import com.design.reader.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class BookDetailActivity extends BaseActivity<BookDetailView, BookDetailPresenter> implements BookDetailView {


    @BindView(R.id.book_detail_image)
    ImageView bookDetailImage;
    @BindView(R.id.bool_detail_name)
    TextView boolDetailName;
    @BindView(R.id.book_detail_text)
    TextView bookDetailText;
    @BindView(R.id.buy_book_button)
    Button buyBookButton;
    @BindView(R.id.rent_book_button)
    Button rentBookButton;

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

    @OnClick(R.id.book_detail_back)
    void back(){
        finish();
    }
}
