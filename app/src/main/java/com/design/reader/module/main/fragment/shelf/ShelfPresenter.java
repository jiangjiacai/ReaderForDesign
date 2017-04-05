package com.design.reader.module.main.fragment.shelf;

import android.Manifest;
import android.app.Activity;

import com.design.reader.api.ReaderApi;
import com.design.reader.base.BasePresenter;
import com.design.reader.base.MyApplication;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;

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

    public void initPermission(Activity activity) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Action1<Permission>() {
            @Override
            public void call(Permission permission) {
                if (isViewAttached()) {
                    if (permission.granted) {
                        getView().grantedPermission();
                    } else {
                        getView().permissionFailed();
                    }
                }
            }
        });
    }

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
