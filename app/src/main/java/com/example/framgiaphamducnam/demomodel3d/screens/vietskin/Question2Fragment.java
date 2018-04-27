package com.example.framgiaphamducnam.demomodel3d.screens.vietskin;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
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
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.lnA)
    LinearLayout lnA;
    @BindView(R.id.lnB)
    LinearLayout lnB;
    @BindView(R.id.lnC)
    LinearLayout lnC;
    @BindView(R.id.lnD)
    LinearLayout lnD;
    @BindView(R.id.viewA)
    View viewA;
    @BindView(R.id.viewB)
    View viewB;
    @BindView(R.id.viewC)
    View viewC;
    @BindView(R.id.viewD)
    View viewD;
    boolean stateChangedA = false;
    boolean stateChangedB = false;
    boolean stateChangedC = false;
    boolean stateChangedD = false;
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
    public void onClickBack() {
        mActivity.popFragmentQ1();
    }

    @OnClick(R.id.btnNext)
    public void onClickNext() {
        //mActivity.pushFragmentQ1(new Question2Fragment());
        Toast.makeText(mActivity, "OK", Toast.LENGTH_SHORT).show();
    }

    @OnClick({ R.id.lnA, R.id.lnB, R.id.lnC, R.id.lnD})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lnA:
                clickQuestionA();
                break;
            case R.id.lnB:
                clickQuestionB();
                break;
            case R.id.lnC:
                clickQuestionC();
                break;
            case R.id.lnD:
                clickQuestionD();
                break;
        }
    }

    private void clickQuestionA() {
        if (stateChangedA) {
            // reset background to default;
            lnA.setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.bg_select_question_uncheck));
            viewA.setBackgroundDrawable(getResources().getDrawable(R.color.colorBackground));
            lnA.setSelected(false);
        } else {
            lnA.setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.bg_select_question_check));
            viewA.setBackgroundDrawable(getResources().getDrawable(R.color.white));
            lnA.setSelected(true);
        }
        stateChangedA = !stateChangedA;
    }
    private void clickQuestionB() {
        if (stateChangedB) {
            // reset background to default;
            lnB.setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.bg_select_question_uncheck));
            viewB.setBackgroundDrawable(getResources().getDrawable(R.color.colorBackground));
            lnB.setSelected(false);
        } else {
            lnB.setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.bg_select_question_check));
            viewB.setBackgroundDrawable(getResources().getDrawable(R.color.white));
            lnB.setSelected(true);
        }
        stateChangedB = !stateChangedB;
    }
    private void clickQuestionC() {
        if (stateChangedC) {
            // reset background to default;
            lnC.setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.bg_select_question_uncheck));
            viewC.setBackgroundDrawable(getResources().getDrawable(R.color.colorBackground));
            lnC.setSelected(false);
        } else {
            lnC.setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.bg_select_question_check));
            viewC.setBackgroundDrawable(getResources().getDrawable(R.color.white));
            lnC.setSelected(true);
        }
        stateChangedC = !stateChangedC;
    }
    private void clickQuestionD() {
        if (stateChangedD) {
            // reset background to default;
            lnD.setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.bg_select_question_uncheck));
            viewD.setBackgroundDrawable(getResources().getDrawable(R.color.colorBackground));
            lnD.setSelected(false);
        } else {
            lnD.setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.bg_select_question_check));
            viewD.setBackgroundDrawable(getResources().getDrawable(R.color.white));
            lnD.setSelected(true);
        }
        stateChangedD = !stateChangedD;
    }
}
