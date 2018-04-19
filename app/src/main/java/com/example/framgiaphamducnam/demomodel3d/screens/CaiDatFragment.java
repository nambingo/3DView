package com.example.framgiaphamducnam.demomodel3d.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.example.framgiaphamducnam.demomodel3d.R;

/**
 * Created by FRAMGIA\pham.duc.nam on 18/04/2018.
 */

public class CaiDatFragment extends Fragment{
    private View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_cai_dat, container, false);
            ButterKnife.bind(this, mView);
            //mActivity = (MainActivity2) getActivity();
            //initData();
        }
        return mView;
    }
}
