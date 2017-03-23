package com.design.reader.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.design.reader.R;
import com.design.reader.module.main.fragment.shelf.ShelfFragment;
import com.design.reader.module.main.fragment.shelf.leased.LeasedFragment;
import com.design.reader.module.main.fragment.shelf.purchased.PurchasedFragment;

import java.util.ArrayList;
import java.util.List;


public class ShelfFragmentPagerAdapter extends FragmentPagerAdapter {
    LeasedFragment leasedFragment;
    PurchasedFragment purchasedFragment;

    private List<String> titleList;

    public ShelfFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        leasedFragment = new LeasedFragment();
        purchasedFragment = new PurchasedFragment();
        titleList = new ArrayList<>();
        titleList.add("已购买");
        titleList.add("已租赁");
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case ShelfFragment.LEASED:
                fragment = leasedFragment;
                break;
            case ShelfFragment.PURCHASED:
                fragment = purchasedFragment;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
