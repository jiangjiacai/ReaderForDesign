package com.design.reader.module.login;

import com.design.reader.module.main.MainView;

public interface LoginView extends MainView {

    void loginError(String msg);

    void loginSuccess();
}
