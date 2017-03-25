package com.design.reader.module.read;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.design.reader.R;
import com.design.reader.base.MyApplication;

import java.io.File;
import java.io.IOException;

public class ReadActivity extends AppCompatActivity {

    private PageWidget mPageWidget;
    Bitmap mCurPageBitmap, mNextPageBitmap;
    Canvas mCurPageCanvas, mNextPageCanvas;
    BookPageFactory pagefactory;
    private int screenWidth;
    private int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mPageWidget = new PageWidget(this);
        setContentView(mPageWidget);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
//        screenWidth = 480;
//        screenHeight = 800;

        mCurPageBitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
        mNextPageBitmap = Bitmap
                .createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);

        mCurPageCanvas = new Canvas(mCurPageBitmap);
        mNextPageCanvas = new Canvas(mNextPageBitmap);
        pagefactory = new BookPageFactory(screenWidth, screenHeight);

        pagefactory.setBgBitmap(BitmapFactory.decodeResource(
                this.getResources(), R.drawable.bg));

        try {
//            pagefactory.openbook("/sdcard/test.txt");
            String path = Environment.getExternalStorageDirectory().getPath() + "/test.txt";
            Log.e("PATH", "onCreate: " + path);
            pagefactory.openbook(path);
            pagefactory.onDraw(mCurPageCanvas);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            Toast.makeText(this, "电子书不存在,请将《test.txt》放在SD卡根目录下",
                    Toast.LENGTH_SHORT).show();
        }

        mPageWidget.setBitmaps(mCurPageBitmap, mCurPageBitmap);
        mPageWidget.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                // TODO Auto-generated method stub

                boolean ret = false;
                if (v == mPageWidget) {
                    if (e.getAction() == MotionEvent.ACTION_DOWN) {
                        mPageWidget.abortAnimation();
                        mPageWidget.calcCornerXY(e.getX(), e.getY());

                        pagefactory.onDraw(mCurPageCanvas);
                        if (mPageWidget.DragToRight()) {
                            try {
                                pagefactory.prePage();
                            } catch (IOException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            if (pagefactory.isfirstPage()) return false;
                            pagefactory.onDraw(mNextPageCanvas);
                        } else {
                            try {
                                pagefactory.nextPage();
                            } catch (IOException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            if (pagefactory.islastPage()) return false;
                            pagefactory.onDraw(mNextPageCanvas);
                        }
                        mPageWidget.setBitmaps(mCurPageBitmap, mNextPageBitmap);
                    }

                    ret = mPageWidget.doTouchEvent(e);
                    return ret;
                }
                return false;
            }

        });
    }
}
