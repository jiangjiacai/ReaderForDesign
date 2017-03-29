package com.design.reader.module.setting;

import android.os.Bundle;

import com.design.reader.R;
import com.design.reader.base.BaseActivity;

public class SettingActivity extends BaseActivity<SettingView, SettingPresenter> implements SettingView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    @Override
    public void initViews() {

    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    protected SettingPresenter createPresenter() {
        return null;
    }
}
