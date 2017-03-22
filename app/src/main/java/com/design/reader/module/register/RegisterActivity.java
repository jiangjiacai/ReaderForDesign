package com.design.reader.module.register;

import android.widget.Button;
import android.widget.EditText;

import com.design.reader.R;
import com.design.reader.base.BaseActivity;

import butterknife.BindView;

public class RegisterActivity extends BaseActivity<RegisterView, RegisterPresenter> implements RegisterView {

    @BindView(R.id.edit_phone_number_register)
    EditText editPhoneNumberRegister;
    @BindView(R.id.edit_password_register)
    EditText editPasswordRegister;
    @BindView(R.id.edit_password_again)
    EditText editPasswordAgain;
    @BindView(R.id.register_register)
    Button registerRegister;
    @BindView(R.id.login_register)
    Button loginRegister;

    @Override
    public void initViews() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }
}
