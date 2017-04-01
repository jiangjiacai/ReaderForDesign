package com.design.reader.module.main.fragment.shelf;

import com.design.reader.api.ReaderApi;
import com.design.reader.base.BasePresenter;
import com.design.reader.base.MyApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ShelfPresenter extends BasePresenter<ShelfView> {

    Subscription downloadSubscription;

    public void downloadBook(String url) {
//        downloadSubscription = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//
//            }
//        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//
//            }
//        });

        downloadSubscription = ReaderApi.getInstance().downloadBook(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<ResponseBody>() {
            @Override
            public void call(ResponseBody responseBody) {

            }
        });
    }

    private boolean writeToDisk(ResponseBody responseBody) {

        File file = new File(MyApplication.getContext().getFilesDir() + File.separator + "userId", "book");
        InputStream is;
        OutputStream os = null;
        is = responseBody.byteStream();
        try {
            byte[] bytes = new byte[4096];
            long contentLength = responseBody.contentLength();
            long downloadSize = 0;
            os = new FileOutputStream(file);
            int read = 0;
            while ((read = is.read(bytes)) != -1) {
                os.write(bytes, 0, read);
                downloadSize += read;
            }
            os.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
