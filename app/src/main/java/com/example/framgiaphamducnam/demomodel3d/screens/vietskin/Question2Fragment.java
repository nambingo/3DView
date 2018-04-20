package com.example.framgiaphamducnam.demomodel3d.screens.vietskin;

import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.framgiaphamducnam.demomodel3d.MainActivity;
import com.example.framgiaphamducnam.demomodel3d.R;
import com.example.framgiaphamducnam.demomodel3d.base.BaseFragment;

/**
 * Created by FRAMGIA\pham.duc.nam on 19/04/2018.
 */

public class Question2Fragment extends BaseFragment {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    private MainActivity mActivity;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_q_2;
    }

    @Override
    protected void createView(View view) {
        mActivity = (MainActivity) getActivity();
    }

    @OnClick(R.id.btnBack)
    public void onBack() {
        mActivity.popFragmentQ1();
    }
}
