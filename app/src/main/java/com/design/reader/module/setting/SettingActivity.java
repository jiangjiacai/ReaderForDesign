package com.design.reader.module.setting;

import com.design.reader.R;
import com.design.reader.base.BaseActivity;

public class SettingActivity extends BaseActivity<SettingView, SettingPresenter> implements SettingView {


    @Override
    public void initViews() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected SettingPresenter createPresenter() {
        return new SettingPresenter();
    }
}
