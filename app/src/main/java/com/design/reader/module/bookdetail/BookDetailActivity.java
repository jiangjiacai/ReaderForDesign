package com.design.reader.module.bookdetail;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.design.reader.R;
import com.design.reader.base.BaseActivity;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import cn.carbswang.android.numberpickerview.library.NumberPickerView;

public class BookDetailActivity extends BaseActivity<BookDetailView, BookDetailPresenter> implements BookDetailView {


    @BindView(R.id.book_detail_image)
    ImageView bookDetailImage;
    @BindView(R.id.bool_detail_name)
    TextView boolDetailName;
    @BindView(R.id.book_detail_text)
    TextView bookDetailText;
    @BindView(R.id.buy_book_button)
    Button buyBookButton;
    @BindView(R.id.rent_book_button)
    Button rentBookButton;
    @BindView(R.id.detail_count)
    TextView count;

    @Override
    public void initViews() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_book_detail;
    }

    @Override
    protected BookDetailPresenter createPresenter() {
        return new BookDetailPresenter();
    }

    @OnClick({R.id.book_detail_back, R.id.buy_book_button, R.id.rent_book_button, R.id.detail_count})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.book_detail_back:
                finish();
                break;
            case R.id.buy_book_button:
                new AlertDialog.Builder(this).setTitle("提示").setMessage("确认购买此书吗？您选择了" + count.getText() + "本，是否继续？").setPositiveButton(getResources().getString(R.string.sure), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
                break;
            case R.id.rent_book_button:
                new AlertDialog.Builder(this).setTitle("提示").setMessage("确认租赁此书吗？您选择了" + count.getText() + "本，是否继续？").setPositiveButton(getResources().getString(R.string.sure), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
                break;
            case R.id.detail_count:
                final android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(this).create();
                dialog.show();
                Window window = dialog.getWindow();
                //为dialog设置布局
                window.setContentView(R.layout.number_picker_layout);
                //定义数字选择器并设置参数
                final NumberPickerView numberPickerView = (NumberPickerView) window.findViewById(R.id.weight_picker);
                TextView textView = (TextView) window.findViewById(R.id.number_pick_sure);
                numberPickerView.setDisplayedValues(getValue());
                numberPickerView.setMinValue(1);
                numberPickerView.setMaxValue(299);
                numberPickerView.setValue(1);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        count.setText("" + numberPickerView.getValue());
                        dialog.dismiss();
                    }
                });
                break;
        }
    }

    private String[] getValue() {
        int[] data = new int[300];
        for (int i = 0; i < 300; i++) {
            data[i] = i;
        }
        return Arrays.toString(data).split("[\\[\\]]")[1].split(", ");
    }
}
