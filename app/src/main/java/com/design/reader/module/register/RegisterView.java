package com.design.reader.module.register;


import com.design.reader.base.BaseView;

public interface RegisterView extends BaseView {

    void registerError(String msg);

    void registerSuccess();
}
