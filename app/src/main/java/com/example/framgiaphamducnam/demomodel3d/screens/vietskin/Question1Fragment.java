package com.example.framgiaphamducnam.demomodel3d.screens.vietskin;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.framgiaphamducnam.demomodel3d.MainActivity;
import com.example.framgiaphamducnam.demomodel3d.R;
import com.example.framgiaphamducnam.demomodel3d.base.BaseFragment;

/**
 * Created by FRAMGIA\pham.duc.nam on 19/04/2018.
 */

public class Question1Fragment extends BaseFragment {
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.btnNext)
    Button btnNext;
    private MainActivity mActivity;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_q_1;
    }

    @Override
    protected void createView(View view) {
        mActivity = (MainActivity) getActivity();
    }

    @OnClick(R.id.btnBack)
    public void onClickBack() {
        mActivity.popFragmentQ1();
    }

    @OnClick(R.id.btnNext)
    public void onClickNext() {
        mActivity.pushFragmentQ1(new Question2Fragment());
    }
}
