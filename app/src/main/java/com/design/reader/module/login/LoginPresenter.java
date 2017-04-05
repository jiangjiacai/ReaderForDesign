package com.design.reader.module.login;

import android.content.Context;
import android.content.Intent;

import com.design.reader.base.BasePresenter;
import com.design.reader.entity.db.User;
import com.design.reader.module.register.RegisterActivity;
import com.design.reader.tools.SharedPreferenceUtils;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;


public class LoginPresenter extends BasePresenter<LoginView> {

    public void register(final Context context) {
        //打开注册页面
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
// 解析注册结果
                if (result == SMSSDK.RESULT_COMPLETE) {
                    @SuppressWarnings("unchecked")
                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country");
                    String phone = (String) phoneMap.get("phone");
                    jumpToRegister(context, country, phone);
                }
            }
        });
        registerPage.show(context);
    }

    public void login(String userName, String passWord, boolean isRememberPassword) {
        if (userName == null || passWord == null) {
            getView().loginError("请填写完整的用户信息!");
            return;
        }
        //登录成功后执行
        if (isRememberPassword) {
            SharedPreferenceUtils.getInstance().putString(SharedPreferenceUtils.SHARED_PHONE, userName).putString(SharedPreferenceUtils.SHARED_PASSWORD, passWord);
        } else {
            SharedPreferenceUtils.getInstance().putString(SharedPreferenceUtils.SHARED_PHONE, "").putString(SharedPreferenceUtils.SHARED_PASSWORD, "");
        }
        User user = new User();
        user.setNumber(userName);
        user.saveOrUpdate();
        if (isViewAttached()) {
            getView().loginSuccess();
        }
    }

    private void jumpToRegister(Context context, String country, String phone) {
        Intent intent = new Intent(context, RegisterActivity.class);
        intent.putExtra("phone", phone);
        context.startActivity(intent);
    }

}
