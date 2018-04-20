package com.example.framgiaphamducnam.demomodel3d.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.example.framgiaphamducnam.demomodel3d.utils.ProgressDialogUtil;

/**
 * Created by FRAMGIA\pham.duc.nam on 20/04/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected ProgressDialogUtil mProgressDialogUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        createView();
        mProgressDialogUtil = new ProgressDialogUtil(this);
    }

    protected abstract int getLayoutId();

    protected abstract void createView();

    public void showActivity(Class t) {
        Intent intent = new Intent(this, t);
        startActivity(intent);
    }

    public void showActivity(Class t, Bundle bundle) {
        Intent intent = new Intent(this, t);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void makeToast(String msg) {
        if (msg != null) {
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "null", Toast.LENGTH_LONG).show();
        }
    }

    public void makeToast(int msgId) {
        String msg = getString(msgId);
        makeToast(msg);
    }

    public void hideLoading() {
        if (mProgressDialogUtil != null && mProgressDialogUtil.isShowing()) {
            mProgressDialogUtil.dismiss();
        }

    }

    public void showLoad() {
        if (mProgressDialogUtil != null && !mProgressDialogUtil.isShowing()) {
            mProgressDialogUtil.show(false);
        }
    }


}
