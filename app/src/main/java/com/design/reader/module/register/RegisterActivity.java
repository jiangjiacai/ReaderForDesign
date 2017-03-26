package com.design.reader.module.register;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.design.reader.R;
import com.design.reader.base.BaseActivity;
import com.design.reader.base.MyApplication;

import butterknife.BindView;
import butterknife.OnClick;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends BaseActivity<RegisterView, RegisterPresenter> implements RegisterView {

    @BindView(R.id.phone_number_register_text)
    TextView phoneTest;
    @BindView(R.id.edit_password_register)
    EditText editPasswordRegister;
    @BindView(R.id.edit_password_again)
    EditText editPasswordAgain;
    @BindView(R.id.register_register)
    Button registerRegister;
    @BindView(R.id.login_register)
    Button loginRegister;
    @BindView(R.id.register_toolbar)
    Toolbar toolbar;

    @Override
    public void initViews() {
        SMSSDK.initSDK(this, MyApplication.MOBAppKey, MyApplication.MOBAppSecrete);
//        mPresenter.initRegister();
        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        phoneTest.setText(phone);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @OnClick(R.id.register_register)
    void register(View v) {
//        mPresenter.register(phoneTest.getText().toString().trim());
    }

    @OnClick(R.id.register_toolbar)
    void back() {
        finish();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void registerError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerSuccess() {

    }
}
