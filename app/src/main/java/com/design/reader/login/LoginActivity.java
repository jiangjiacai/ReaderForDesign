package com.design.reader.login;

import com.design.reader.base.BaseActivity;
import com.design.reader.R;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> {

    @Override
    public void initViews() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }
}
