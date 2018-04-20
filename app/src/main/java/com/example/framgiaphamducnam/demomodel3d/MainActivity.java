package com.example.framgiaphamducnam.demomodel3d;

import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.framgiaphamducnam.demomodel3d.base.BaseActivity;
import com.example.framgiaphamducnam.demomodel3d.screens.khamonline.KhamOnlineFragment;
import com.example.framgiaphamducnam.demomodel3d.screens.setting.CaiDatFragment;
import com.example.framgiaphamducnam.demomodel3d.screens.vietskin.VietSkinFragment;
import com.example.framgiaphamducnam.demomodel3d.utils.FragNavController;
import com.google.gson.Gson;
import com.unity3d.player.UnityPlayer;

/**
 * Created by FRAMGIA\pham.duc.nam on 18/04/2018.
 */

public class MainActivity extends BaseActivity
        implements FragNavController.RootFragmentListener {

    private static int[] Data = {0, 1, 2};
    String mStringData = "";
    //index for tabs in main screen
    private final int INDEX_VIET_SKIN = FragNavController.TAB_1;
    private final int INDEX_KHAM_ONLINE = FragNavController.TAB_2;
    private final int INDEX_CAI_DAT = FragNavController.TAB_3;
    public static UnityPlayer mUnityPlayer;
    //fragment manager
    public FragNavController mFragNavController;
    @BindView(R.id.nav)
    View bottomTab;

    @BindView(R.id.rlVietSkin)
    RelativeLayout rlVietSkin;

    @BindView(R.id.rlKhamOnline)
    RelativeLayout rlKhamOnline;

    @BindView(R.id.rlCaiDat)
    RelativeLayout rlCaiDat;

    @BindView(R.id.tvVietSkin)
    TextView tvVietSkin;

    @BindView(R.id.tvKhamOnline)
    TextView tvKhamOnline;

    @BindView(R.id.tvCaiDat)
    TextView tvCaiDat;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    @Override
    protected int getLayoutId() {
        return R.layout.main;
    }

    @Override
    protected void createView() {

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
        getData(Data);
    }

    public void getData(int[] Data){
        mStringData = new Gson().toJson(Data);
    }

    public String sendData(){
        if (TextUtils.isEmpty(mStringData)) return null;
        return mStringData;
    }

    @OnClick({ R.id.rlVietSkin, R.id.rlKhamOnline, R.id.rlCaiDat })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlVietSkin:
                selectItem(INDEX_VIET_SKIN);
                mFragNavController.switchTab(INDEX_VIET_SKIN);
                break;
            case R.id.rlKhamOnline:
                selectItem(INDEX_KHAM_ONLINE);
                mFragNavController.switchTab(INDEX_KHAM_ONLINE);
                break;
            case R.id.rlCaiDat:
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
            mFragNavController.pop(0,0);
        } else {
            super.onBackPressed();
        }
    }

    //protected void showFragment(Fragment fragment) {
    //
    //    String TAG = fragment.getClass().getSimpleName();
    //    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    //
    //    fragmentTransaction.replace(R.id.mainContainer, fragment, TAG);
    //    fragmentTransaction.addToBackStack(null);
    //    fragmentTransaction.commitAllowingStateLoss();
    //}

    public void pushFragment(Fragment fragment) {
        if (mFragNavController != null) {
            mFragNavController.push(fragment, R.anim.enter_from_right, R.anim.exit_to_left);
        }
    }

    public void popFragment(){
        if (mFragNavController != null) {
            mFragNavController.pop(R.anim.enter_from_left, R.anim.exit_to_right);
        }
    }

    public void pushFragmentQ1(Fragment fragment) {
        if (mFragNavController != null) {
            mFragNavController.push(fragment, 0, 0);
        }
    }

    public void popFragmentQ1(){
        if (mFragNavController != null) {
            mFragNavController.pop(0, 0);
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
