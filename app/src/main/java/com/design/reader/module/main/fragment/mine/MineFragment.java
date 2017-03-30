package com.design.reader.module.main.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.design.reader.R;
import com.design.reader.base.BaseFragment;
import com.design.reader.module.setting.SettingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineFragment extends BaseFragment<MineView, MinePresenter> implements MineView {
    @BindView(R.id.setting_button)
    ImageView settingButton;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_name_layout)
    LinearLayout userNameLayout;
    @BindView(R.id.user_phone)
    TextView userPhone;
    @BindView(R.id.user_phone_layout)
    LinearLayout userPhoneLayout;
    @BindView(R.id.user_state)
    TextView userState;
    @BindView(R.id.user_wallet)
    TextView userWallet;
    @BindView(R.id.purchased_count)
    TextView purchasedCount;
    @BindView(R.id.leased_count)
    TextView leasedCount;

    @Override
    public void initViews(View view) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @OnClick({R.id.setting_button})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_button:
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    public MinePresenter createPresenter() {
        return new MinePresenter();
    }
}
