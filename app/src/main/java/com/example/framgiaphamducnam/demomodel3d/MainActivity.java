package com.example.framgiaphamducnam.demomodel3d;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.example.framgiaphamducnam.demomodel3d.utils.ProgressDialogUtil;
import com.unity3d.player.UnityPlayer;

public class MainActivity extends Activity {
    private final int TIME_LOADING = 7000;

    public UnityPlayer mUnityPlayer; // don't change the name of this variable; referenced from native code
    //=================
    private FrameLayout fl_forUnity;
    private RelativeLayout rlLoading;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //if (Build.VERSION.SDK_INT < 16) {
        //    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //}
        //else {
        //    View decorView = getWindow().getDecorView();
        //    // Show Status Bar.
        //    int uiOptions = View.SYSTEM_UI_FLAG_VISIBLE;
        //    decorView.setSystemUiVisibility(uiOptions);
        //}
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        rlLoading = findViewById(R.id.rlLoading);
        btnNext = findViewById(R.id.btnNext);
        rlLoading.setVisibility(View.VISIBLE);
        btnNext.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                rlLoading.setVisibility(View.GONE);
                btnNext.setVisibility(View.VISIBLE);
            }
        }, TIME_LOADING);
        getWindow().setFormat(PixelFormat.RGBX_8888);
        mUnityPlayer = new UnityPlayer(this);
        int glesMode = mUnityPlayer.getSettings().getInt("gles_mode", 1);
        boolean trueColor8888 = false;
        mUnityPlayer.init(glesMode, trueColor8888);
        fl_forUnity = findViewById(R.id.fl_forUnity);
        fl_forUnity.addView(mUnityPlayer.getView(),
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        mUnityPlayer.requestFocus();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUnityPlayer.UnitySendMessage("GameManager", "GetListChecked","");
            }
        });
    }

    public void ReturnChecked(String value){
        Toast.makeText(this, "OK!",Toast.LENGTH_SHORT).show();
    }

    private void setupButton(){

    }

    @Override
    public void onResume() {
        super.onResume();
        mUnityPlayer.resume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fl_forUnity.removeAllViewsInLayout();
        fl_forUnity.removeAllViews();
        mUnityPlayer.quit();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mUnityPlayer.pause();
    }

    //    // This ensures the layout will be correct.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mUnityPlayer.configurationChanged(newConfig);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mUnityPlayer.windowFocusChanged(hasFocus);
    }

    // For some reason the multiple keyevent type is not supported by the ndk.
    // Force event injection by overriding dispatchKeyEvent().
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_MULTIPLE)
            return mUnityPlayer.injectEvent(event);
        return super.dispatchKeyEvent(event);
    }

    // Pass any events not handled by (unfocused) views straight to UnityPlayer
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return mUnityPlayer.injectEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mUnityPlayer.injectEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mUnityPlayer.injectEvent(event);
    }

    /*API12*/
    public boolean onGenericMotionEvent(MotionEvent event) {
        return mUnityPlayer.injectEvent(event);
    }
}
