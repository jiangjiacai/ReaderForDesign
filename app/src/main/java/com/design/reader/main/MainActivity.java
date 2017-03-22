package com.design.reader.main;

import com.design.reader.R;
import com.design.reader.base.BaseActivity;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

    @Override
    public void initViews() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }
}
