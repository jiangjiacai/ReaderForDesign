package com.design.reader.module.main.fragment.mine;

import android.view.View;

import com.design.reader.R;
import com.design.reader.base.BaseFragment;

public class MineFragment extends BaseFragment<MineView, MinePresenter> implements MineView {
    @Override
    public void initViews(View view) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public MinePresenter createPresenter() {
        return new MinePresenter();
    }
}
