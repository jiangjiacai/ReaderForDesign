package com.design.reader.module.register;


import android.util.Log;

import com.design.reader.base.BasePresenter;

import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;

public class RegisterPresenter extends BasePresenter<RegisterView> {

    EventHandler eventHandler;

    public void initRegister() {
        eventHandler = new EventHandler() {

            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功


                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                }
            }
        };

        SMSSDK.registerEventHandler(eventHandler);

    }

    public void register(String phone) {
        SMSSDK.getSupportedCountries();
        SMSSDK.getVerificationCode("86", phone, new OnSendMessageHandler() {
            @Override
            public boolean onSendMessage(String s, String s1) {
                Log.e("SMS", "onSendMessage: " + s + ",s1:" + s1);
                return false;
            }
        });
    }

    public void onDestroy() {
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}
