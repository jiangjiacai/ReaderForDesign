package com.design.reader.main.fragment.setting;

import android.view.View;

import com.design.reader.R;
import com.design.reader.base.BaseFragment;

public class SettingFragment extends BaseFragment<SettingView, SettingPresenter> implements SettingView {
    @Override
    public void initViews(View view) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    public SettingPresenter createPresenter() {
        return new SettingPresenter();
    }
}
