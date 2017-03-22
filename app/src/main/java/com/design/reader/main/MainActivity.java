package com.design.reader.main;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.design.reader.R;
import com.design.reader.base.BaseActivity;
import com.design.reader.main.fragment.mine.MineFragment;
import com.design.reader.main.fragment.setting.SettingFragment;
import com.design.reader.main.fragment.shelf.ShelfFragment;
import com.design.reader.main.fragment.store.StoreFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView, RadioGroup.OnCheckedChangeListener {

    ShelfFragment shelfFragment;
    StoreFragment storeFragment;
    MineFragment mineFragment;
    SettingFragment settingFragment;
    FragmentManager fragmentManager;

    @BindView(R.id.main_group)
    RadioGroup radioGroup;

    @BindView(R.id.shelf_pager)
    RadioButton shelfRadioButton;

    @Override
    public void initViews() {
        fragmentManager = getSupportFragmentManager();
        radioGroup.setOnCheckedChangeListener(this);
        shelfRadioButton.setChecked(true);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        hideAllFragment(ft);
        switch (checkedId) {
            case R.id.shelf_pager:
                showShelfFragment(ft);
                break;
            case R.id.store_pager:
                showStoreFragment(ft);
                break;
            case R.id.mine_pager:
                showMineFragment(ft);
                break;
            case R.id.setting_pager:
                showSettingFragment(ft);
                break;
        }
        ft.commit();
    }

    /**
     * 隐藏所有fragment
     */
    private void hideAllFragment(FragmentTransaction transaction) {
        if (shelfFragment != null)
            transaction.hide(shelfFragment);
        if (storeFragment != null)
            transaction.hide(storeFragment);
        if (mineFragment != null)
            transaction.hide(mineFragment);
        if (settingFragment != null) {
            transaction.hide(settingFragment);
        }
    }

    private void showShelfFragment(FragmentTransaction transaction) {
        if (shelfFragment == null) {
            shelfFragment = new ShelfFragment();
            transaction.add(R.id.main_fragment, shelfFragment);
        } else {
            transaction.show(shelfFragment);
        }
    }

    private void showStoreFragment(FragmentTransaction transaction) {
        if (storeFragment == null) {
            storeFragment = new StoreFragment();
            transaction.add(R.id.main_fragment, storeFragment);
        } else {
            transaction.show(storeFragment);
        }
    }

    private void showMineFragment(FragmentTransaction transaction) {
        if (mineFragment == null) {
            mineFragment = new MineFragment();
            transaction.add(R.id.main_fragment, mineFragment);
        } else {
            transaction.show(mineFragment);
        }
    }

    private void showSettingFragment(FragmentTransaction transaction) {
        if (settingFragment == null) {
            settingFragment = new SettingFragment();
            transaction.add(R.id.main_fragment, settingFragment);
        } else {
            transaction.show(settingFragment);
        }
    }


}
