package com.feoul.customviewdemo2;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private CustomLoadingView mcv_view;
    private ImageView iv_fan;
    private static final int REFRESH_PROGRESS = 0x10;
    private int mProgress = 0;
    private CustomLoadingView mLoadingView;
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_PROGRESS:
                    if (mProgress < 95) {
                        mProgress += 1;
                        // 随机800ms以内刷新一次
                        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
                                new Random().nextInt(200));
                        mLoadingView.setProgress(mProgress);
                    }else {
                        iv_fan.clearAnimation();
                    }

                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RotateAnimation rotateAnimation = AnimationUtils.initRotateAnimation(false, 1500, true,
                Animation.INFINITE);
        mLoadingView = (CustomLoadingView) findViewById(R.id.progress_loading);
        iv_fan = (ImageView) findViewById(R.id.fan_pic);
        iv_fan.startAnimation(rotateAnimation);
        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS, 3000);

    }
}
