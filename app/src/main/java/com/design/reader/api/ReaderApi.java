package com.design.reader.api;


import com.design.reader.base.Constant;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class ReaderApi {

    private static volatile ReaderApi instance;
    private ReaderApiService service;

    private ReaderApi() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ReaderApiService.class);

    }

    public static ReaderApi getInstance() {
        if (instance == null) {
            synchronized (ReaderApi.class) {
                if (instance == null) {
                    instance = new ReaderApi();
                }
            }
        }
        return instance;
    }

    public Observable<String> login(String userName, String password) {
        return service.login(userName, password);
    }

    public Observable<String> register(String userName, String password) {
        return service.login(userName, password);
    }

    public Observable<ResponseBody> downloadBook(String url) {
        return service.downloadBook(url);
    }
}
