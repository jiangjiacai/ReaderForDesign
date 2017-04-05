package com.design.reader.module.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.design.reader.R;
import com.design.reader.base.BaseActivity;
import com.design.reader.base.MyApplication;
import com.design.reader.module.main.MainActivity;
import com.design.reader.tools.SharedPreferenceUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.smssdk.SMSSDK;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {

    @BindView(R.id.edit_phone_number)
    EditText editPhoneNumber;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.remember_password)
    CheckBox rememberPassword;
    @BindView(R.id.forgot_password)
    TextView forgotPassword;
    @BindView(R.id.register)
    Button register;

    @Override
    public void initViews() {
        SMSSDK.initSDK(this, MyApplication.MOBAppKey, MyApplication.MOBAppSecrete);
        if (SharedPreferenceUtils.getInstance().getBoolean(SharedPreferenceUtils.AUTO_LOGIN)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.register, R.id.login})
    void doOnClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                mPresenter.register(this);
                break;
            case R.id.login:
                mPresenter.login(editPhoneNumber.getText().toString().trim(), editPassword.getText().toString().trim(), rememberPassword.isChecked());
                break;
        }
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void loginError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {
        if (rememberPassword.isChecked()) {
            SharedPreferenceUtils.getInstance().putBoolean(SharedPreferenceUtils.AUTO_LOGIN, true);
        } else {
            SharedPreferenceUtils.getInstance().putBoolean(SharedPreferenceUtils.AUTO_LOGIN, false);
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
