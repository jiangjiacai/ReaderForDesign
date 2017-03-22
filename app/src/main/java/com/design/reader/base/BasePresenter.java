package com.design.reader.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BasePresenter<T> {

    public Reference<T> mViewRef;

    public void attacheView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    public T getView() {
        return mViewRef.get();
    }

    public void dettachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

}
