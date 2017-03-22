package com.design.reader.module.main.fragment.store;

import android.view.View;

import com.design.reader.R;
import com.design.reader.base.BaseFragment;

public class StoreFragment extends BaseFragment<StoreView, StorePresenter> implements StoreView {
    @Override
    public void initViews(View view) {

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
