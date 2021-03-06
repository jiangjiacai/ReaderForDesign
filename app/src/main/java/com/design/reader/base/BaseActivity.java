package com.design.reader.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

    public T mPresenter;
    public String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        AppManager.getAppManager().addActivity(this);
        mPresenter = createPresenter();
        mPresenter.attacheView((V) this);
        initViews();
    }

    public abstract void initViews();

    public abstract int getLayoutId();

    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.dettachView();
        AppManager.getAppManager().finishActivity(this);
    }
}
