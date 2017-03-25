package com.design.reader.module.main.fragment.setting;

import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.TextView;

import com.design.reader.R;
import com.design.reader.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingFragment extends BaseFragment<SettingView, SettingPresenter> implements SettingView {
    @BindView(R.id.font_number)
    TextView fontNumber;
    @BindView(R.id.module)
    TextView module;

    @Override
    public void initViews(View view) {

    }

    @OnClick({R.id.module,R.id.font_number})
    void onClick(View v){
        switch (v.getId()){
            case R.id.module:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                getActivity().recreate();
                break;
            case R.id.font_number:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                getActivity().recreate();
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    public SettingPresenter createPresenter() {
        return new SettingPresenter();
    }
}
