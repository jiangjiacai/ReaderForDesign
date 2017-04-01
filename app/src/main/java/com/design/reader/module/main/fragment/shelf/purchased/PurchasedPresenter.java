package com.design.reader.module.main.fragment.shelf.purchased;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.design.reader.R;
import com.design.reader.base.BasePresenter;
import com.design.reader.entity.BookInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class PurchasedPresenter extends BasePresenter<PurchasedView> {

    private List<File> getPathFiles(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        Log.e("TEST", "getPathFiles: ====================" + files.length);
        List<File> fileList = new ArrayList<>();
        for (File f : files) {
            if (f.isDirectory()) continue;
            if (f.getName().endsWith(".txt")) {
                Log.e("T", "==========================add========== ");
                fileList.add(f);
            }
        }
        Log.e("T", "getPathFiles: length:" + fileList.size());
        return fileList;
    }

    public void getFileInfo(final Context context) {

        Observable.create(new Observable.OnSubscribe<List<BookInfo>>() {
            @Override
            public void call(Subscriber<? super List<BookInfo>> subscriber) {
                String path = Environment.getExternalStorageDirectory().getPath();
                Log.e("T", "path:" + path);
                List<File> files = getPathFiles(path);
                List<BookInfo> infos;
                if (files != null && !files.isEmpty()) {
                    infos = new ArrayList<>();
                    Log.e("T", "call: file not null");
                    for (File file : files) {
                        try {
                            InputStream inputStream = new FileInputStream(file);
                            InputStreamReader reader = new InputStreamReader(inputStream, "GBK");
                            BufferedReader bufferedReader = new BufferedReader(reader);
                            String line = "";
                            String content = "";
                            int length = 0;
                            while ((line = bufferedReader.readLine()) != null) {
                                if (length == 5) break;
                                content += line;
                                length++;
                            }
                            inputStream.close();
                            BookInfo bookInfo = new BookInfo();
                            bookInfo.setName(file.getName());
                            bookInfo.setRes(R.mipmap.test);
                            bookInfo.setDescription(content);
                            bookInfo.setPrice(5);
                            infos.add(bookInfo);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    subscriber.onNext(infos);
                    subscriber.onCompleted();
                }
            }
        }).observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<BookInfo>>() {
            @Override
            public void call(List<BookInfo> bookInfos) {
                if (isViewAttached()) {
                    getView().showBookInfo(bookInfos);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (isViewAttached()) {
                    getView().showError("读取出错");
                }
            }
        });
    }

}
