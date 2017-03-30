package com.design.reader.module.setting;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.design.reader.R;
import com.design.reader.base.BaseActivity;
import com.design.reader.module.AboutActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity<SettingView, SettingPresenter> implements SettingView {


    @BindView(R.id.font_number)
    TextView fontNumber;
    @BindView(R.id.font_layout)
    LinearLayout fontLayout;
    @BindView(R.id.module)
    TextView module;
    @BindView(R.id.module_layout)
    LinearLayout moduleLayout;
    @BindView(R.id.about_layout)
    LinearLayout aboutLayout;
    @BindView(R.id.version_number)
    TextView versionNumber;
    @BindView(R.id.version_layout)
    LinearLayout versionLayout;
    @BindView(R.id.logout)
    Button logout;

    @Override
    public void initViews() {

    }

    @OnClick({R.id.logout, R.id.about_layout, R.id.set_back})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout:
                new AlertDialog.Builder(this).setTitle("提示").setPositiveButton(getResources().getString(R.string.sure), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setMessage("确定退出登录?").create().show();
                break;
            case R.id.about_layout:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.set_back:
                finish();
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected SettingPresenter createPresenter() {
        return new SettingPresenter();
    }
}
