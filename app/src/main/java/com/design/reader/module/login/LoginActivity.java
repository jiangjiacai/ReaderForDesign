package com.design.reader.module.login;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.design.reader.R;
import com.design.reader.base.BaseActivity;

import butterknife.BindView;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> {

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
