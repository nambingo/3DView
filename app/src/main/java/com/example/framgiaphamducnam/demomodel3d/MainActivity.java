package com.example.framgiaphamducnam.demomodel3d;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.framgiaphamducnam.demomodel3d.screens.CaiDatFragment;
import com.example.framgiaphamducnam.demomodel3d.screens.KhamOnlineFragment;
import com.example.framgiaphamducnam.demomodel3d.screens.VietSkinFragment;
import com.example.framgiaphamducnam.demomodel3d.utils.FragNavController;
import com.unity3d.player.UnityPlayer;

/**
 * Created by FRAMGIA\pham.duc.nam on 18/04/2018.
 */

public class MainActivity extends AppCompatActivity
        implements FragNavController.RootFragmentListener {

    //index for tabs in main screen
    private final int INDEX_VIET_SKIN = FragNavController.TAB_1;
    private final int INDEX_KHAM_ONLINE = FragNavController.TAB_2;
    private final int INDEX_CAI_DAT = FragNavController.TAB_3;
    public static UnityPlayer mUnityPlayer;
    //fragment manager
    public FragNavController mFragNavController;
    @BindView(R.id.nav)
    View bottomTab;

    @BindView(R.id.tvVietSkin)
    TextView tvVietSkin;

    @BindView(R.id.tvKhamOnline)
    TextView tvKhamOnline;

    @BindView(R.id.tvCaiDat)
    TextView tvCaiDat;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);
        setUpUnity();
        bottomTab.post(new Runnable() {
            @Override
            public void run() {
                AppApplication.getInstance().setBottomTabHeight(bottomTab.getHeight());
                mFragNavController =
                        new FragNavController(savedInstanceState, getSupportFragmentManager(),
                                R.id.main_content, MainActivity.this, 3, INDEX_VIET_SKIN);
                selectItem(INDEX_VIET_SKIN);
            }
        });
    }

    private void setUpUnity() {
        //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFormat(PixelFormat.RGBX_8888);
        mUnityPlayer = new UnityPlayer(this);
        int glesMode = mUnityPlayer.getSettings().getInt("gles_mode", 1);
        boolean trueColor8888 = false;
        mUnityPlayer.init(glesMode, trueColor8888);
    }

    public void ReturnChecked(String value){
        Log.e("MainActivity", "ReturnChecked:  -----> TOAST");
        Toast.makeText(this, "OK!",Toast.LENGTH_SHORT).show();
    }

    @OnClick({ R.id.tvVietSkin, R.id.tvKhamOnline, R.id.tvCaiDat })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvVietSkin:
                selectItem(INDEX_VIET_SKIN);
                mFragNavController.switchTab(INDEX_VIET_SKIN);
                break;
            case R.id.tvKhamOnline:
                selectItem(INDEX_KHAM_ONLINE);
                mFragNavController.switchTab(INDEX_KHAM_ONLINE);
                break;
            case R.id.tvCaiDat:
                selectItem(INDEX_CAI_DAT);
                mFragNavController.switchTab(INDEX_CAI_DAT);
                break;
        }
    }

    @Override
    public Fragment getRootFragment(int index) {
        switch (index) {
            case INDEX_VIET_SKIN:
                return new VietSkinFragment();
            case INDEX_KHAM_ONLINE:
                return new KhamOnlineFragment();
            case INDEX_CAI_DAT:
                return new CaiDatFragment();
        }
        throw new IllegalStateException();
    }

    @Override
    public void onBackPressed() {
        if (mFragNavController.canPop()) {
            mFragNavController.pop();
        } else {
            super.onBackPressed();
        }
    }

    public void pushFragment(Fragment fragment) {
        if (mFragNavController != null) {
            mFragNavController.push(fragment);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mFragNavController != null) {
            mFragNavController.onSaveInstanceState(outState);
        }
    }

    private void selectItem(int INDEX) {
        tvVietSkin.setEnabled(true);
        tvKhamOnline.setEnabled(true);
        tvCaiDat.setEnabled(true);

        switch (INDEX) {
            case INDEX_VIET_SKIN:
                tvVietSkin.setEnabled(false);
                tvVietSkin.setSelected(true);
                tvKhamOnline.setSelected(false);
                tvCaiDat.setSelected(false);
                break;
            case INDEX_KHAM_ONLINE:
                tvKhamOnline.setEnabled(false);
                tvVietSkin.setSelected(false);
                tvKhamOnline.setSelected(true);
                tvCaiDat.setSelected(false);
                break;
            case INDEX_CAI_DAT:
                tvCaiDat.setEnabled(false);
                tvVietSkin.setSelected(false);
                tvKhamOnline.setSelected(false);
                tvCaiDat.setSelected(true);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mUnityPlayer.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
        if (event.getAction() == KeyEvent.ACTION_MULTIPLE) return mUnityPlayer.injectEvent(event);
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
