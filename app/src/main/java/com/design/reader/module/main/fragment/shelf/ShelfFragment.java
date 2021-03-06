package com.design.reader.module.main.fragment.shelf;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.design.reader.R;
import com.design.reader.base.BaseFragment;
import com.design.reader.module.main.fragment.shelf.leased.LeasedFragment;
import com.design.reader.module.main.fragment.shelf.purchased.PurchasedFragment;
import com.design.reader.module.search.SearchActivity;

import butterknife.BindView;
import butterknife.OnClick;

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

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        showPurchasedFragment(fragmentTransaction);
        fragmentTransaction.commit();
//        ShelfFragmentPagerAdapter adapter = new ShelfFragmentPagerAdapter(getChildFragmentManager());
//        shelfViewpager.setAdapter(adapter);
        mPresenter.initPermission(getActivity());
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

    @OnClick(R.id.search_imageView)
    void search(View v) {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        startActivity(intent);
    }

    private void showPurchasedFragment(FragmentTransaction transaction) {
        if (purchasedFragment == null) {
            purchasedFragment = new PurchasedFragment();
            transaction.add(R.id.shelf_frame_layout, purchasedFragment, "purchasedFragment");
        } else {
            transaction.show(purchasedFragment);
        }
    }

    private void showLeasedFragment(FragmentTransaction transaction) {
        if (leasedFragment == null) {
            leasedFragment = new LeasedFragment();
            transaction.add(R.id.shelf_frame_layout, leasedFragment, "leasedFragment");
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

    @Override
    public void grantedPermission() {

    }

    @Override
    public void permissionFailed() {
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle("通知").setMessage("该权限必须获取！请点击确认重新获取").setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.initPermission(getActivity());
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
            }
        }).create();
        dialog.show();
    }
}
