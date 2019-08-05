package com.example.choosedenyapplication;

import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private ConstraintLayout mClDialog;
    private Button mBtnConfirm;
    private Button mBtnCancel;
    private ImageView mImMeToo;

    private int width = 0;  //屏幕宽度：1080
    private int height = 0;  //屏幕高度 ： 1920

    ConstraintLayout.LayoutParams layoutParams;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        mClDialog = findViewById(R.id.cl_dialog);
        mBtnConfirm = findViewById(R.id.btn_confirm);
        mBtnCancel = findViewById(R.id.btn_cancel);
        mImMeToo = findViewById(R.id.im_me_too);

        mBtnConfirm.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);

        initPosition();


    }

    private void changePosition() {
//        Log.d(TAG, "changePosition: left : " + layoutParams.leftMargin + ", top : " + layoutParams.topMargin);
        layoutParams.leftMargin = getRandomLocation(width-600, 0);
        layoutParams.topMargin = getRandomLocation(height-500, 300);
        mClDialog.setLayoutParams(layoutParams);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
//                Log.d(TAG, "onClick: 1111");
                changePosition();
                break;
            case R.id.btn_cancel:
                mClDialog.setVisibility(View.GONE);
                mImMeToo.setVisibility(View.VISIBLE);

                break;
        }
    }

    private void initPosition() {
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        width = point.x;
        height = point.y;
//        Log.d(TAG, "size : width = " + width + ",height = " + height);

        layoutParams = (ConstraintLayout.LayoutParams) mClDialog.getLayoutParams();
        layoutParams.leftMargin = width/2-300;
        layoutParams.topMargin = height/2-250;
        mClDialog.setLayoutParams(layoutParams);
    }

    //获取随机值
    private int getRandomLocation(int max, int min) {
        int location = (int)(min + Math.random()*(max-min+1));
//        Log.d(TAG, "getRandNumber: " + location);
        return location;
    }
}
