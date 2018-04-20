package com.example.framgiaphamducnam.demomodel3d.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.example.framgiaphamducnam.demomodel3d.utils.ProgressDialogUtil;

/**
 * Created by FRAMGIA\pham.duc.nam on 18/04/2018.
 */

public abstract class BaseFragment extends Fragment {
    protected BaseActivity mActivity;
    protected View mView;
    protected ProgressDialogUtil mProgressDialogUtil;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
        mProgressDialogUtil = new ProgressDialogUtil(mActivity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, mView);
            createView(mView);
        }
        return mView;
    }

    @Override
    public void onDestroyView() {
        if (mView.getParent() != null) {
            ((ViewGroup) mView.getParent()).removeView(mView);
        }
        super.onDestroyView();
    }

    protected abstract int getLayoutId();

    protected abstract void createView(View view);

    public void makeToast(String msg) {
        mActivity.makeToast(msg);
    }

    public void makeToast(int msgId) {
        String msg = getString(msgId);
        makeToast(msg);
    }

    public void showActivity(Class t) {
        mActivity.showActivity(t);
    }

    public void showActivity(Class t, Bundle bundle) {
        mActivity.showActivity(t, bundle);
    }

    protected void hideLoading() {
        if (mProgressDialogUtil != null && mProgressDialogUtil.isShowing()) {
            mProgressDialogUtil.dismiss();
        }

    }

    protected void showLoading() {
        if (mProgressDialogUtil != null && !mProgressDialogUtil.isShowing()) {
            mProgressDialogUtil.show(false);
        }
    }

}
