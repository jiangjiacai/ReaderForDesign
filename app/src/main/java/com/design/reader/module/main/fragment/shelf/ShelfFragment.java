package com.design.reader.module.main.fragment.shelf;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.design.reader.R;
import com.design.reader.base.BaseFragment;
import com.design.reader.module.main.fragment.shelf.leased.LeasedFragment;
import com.design.reader.module.main.fragment.shelf.purchased.PurchasedFragment;

import butterknife.BindView;

public class ShelfFragment extends BaseFragment<ShelfView, ShelfPresenter> implements ShelfView, RadioGroup.OnCheckedChangeListener {

    public static final int LEASED = 1;
    public static final int PURCHASED = 0;

    @BindView(R.id.search_imageView)
    ImageView searchImageView;
//    @BindView(R.id.shelf_pager_strip)
//    PagerTabStrip shelfPagerStrip;
//    @BindView(R.id.shelf_viewpager)
//    ViewPager shelfViewpager;

    @BindView(R.id.shelf_radio_group)
    RadioGroup radioGroup;

    private PurchasedFragment purchasedFragment;
    private LeasedFragment leasedFragment;

    @Override
    public void initViews(View view) {
        radioGroup.setOnCheckedChangeListener(this);
        purchasedFragment = new PurchasedFragment();
        leasedFragment = new LeasedFragment();

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        showPurchasedFragment(fragmentTransaction);
        fragmentTransaction.commit();
//        ShelfFragmentPagerAdapter adapter = new ShelfFragmentPagerAdapter(getChildFragmentManager());
//        shelfViewpager.setAdapter(adapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shelf;
    }

    @Override
    public ShelfPresenter createPresenter() {
        return new ShelfPresenter();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        hideAllFragment(ft);
        switch (i) {
            case R.id.purchased_radio_button:
                Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
                showPurchasedFragment(ft);
                break;
            case R.id.leased_radio_button:
                Toast.makeText(getActivity(), "2", Toast.LENGTH_SHORT).show();
                showLeasedFragment(ft);
                break;
        }
        ft.commit();
    }

    private void showPurchasedFragment(FragmentTransaction transaction) {
        if (purchasedFragment == null) {
            purchasedFragment = new PurchasedFragment();
            transaction.add(R.id.shelf_frame_layout, purchasedFragment,"purchasedFragment");
        } else {
            transaction.show(purchasedFragment);
        }
    }

    private void showLeasedFragment(FragmentTransaction transaction) {
        if (leasedFragment == null) {
            leasedFragment = new LeasedFragment();
            transaction.add(R.id.shelf_frame_layout, leasedFragment,"leasedFragment");
        } else {
            transaction.show(leasedFragment);
        }
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (purchasedFragment != null) {
            fragmentTransaction.hide(purchasedFragment);
        }
        if (leasedFragment != null) {
            fragmentTransaction.hide(leasedFragment);
        }
    }
}
