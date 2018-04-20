package com.example.framgiaphamducnam.demomodel3d.screens.vietskin;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.framgiaphamducnam.demomodel3d.MainActivity;
import com.example.framgiaphamducnam.demomodel3d.R;
import com.example.framgiaphamducnam.demomodel3d.utils.DialogUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import static com.example.framgiaphamducnam.demomodel3d.MainActivity.mUnityPlayer;

/**
 * Created by FRAMGIA\pham.duc.nam on 18/04/2018.
 */

public class VietSkinFragment extends Fragment {

    protected View mView;
    @BindView(R.id.rlLoading)
    RelativeLayout rlLoading;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.fl_forUnity)
    FrameLayout fl_forUnity;
    private int[] resultData;
    private String mData;
    private MainActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_viet_skin, container, false);
            ButterKnife.bind(this, mView);
            mActivity = (MainActivity) getActivity();
            initData();
        }
        return mView;
    }

    private void initData() {
        rlLoading.setVisibility(View.VISIBLE);
        btnNext.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                rlLoading.setVisibility(View.GONE);
                btnNext.setVisibility(View.VISIBLE);
            }
        }, 5000);

        fl_forUnity.addView(mUnityPlayer.getView(), FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        mUnityPlayer.requestFocus();
    }

    @OnClick(R.id.btnNext)
    public void onNext() {
        mUnityPlayer.UnitySendMessage("GameManager", "GetListChecked", "");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                receiveData();
            }
        }, 1000);
    }

    private void receiveData() {
        if (TextUtils.isEmpty(mActivity.sendData())) return;
        Type type = new TypeToken<int[]>() {
        }.getType();
        resultData = new Gson().fromJson(mActivity.sendData(), type);
        if (resultData.length == 0) {
            DialogUtils.showDialogAlert(mActivity);
        } else {
            toQuestion1();
        }
    }

    private void toQuestion1() {
        mActivity.pushFragmentQ1(new Question1Fragment());
    }
}
