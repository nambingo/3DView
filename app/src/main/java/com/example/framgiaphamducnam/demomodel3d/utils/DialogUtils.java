package com.example.framgiaphamducnam.demomodel3d.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.framgiaphamducnam.demomodel3d.R;

/**
 * Created by FRAMGIA\pham.duc.nam on 20/04/2018.
 */

public class DialogUtils {
    public static Dialog dialog;

    public static void hideAlert() {
        if (dialog == null) return;
        dialog.dismiss();
    }

    public static Dialog showDialogAlert(Context context){
        dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setContentView(R.layout.dialog_alert);
        dialog.setCanceledOnTouchOutside(true);
        RelativeLayout rlOK = dialog.findViewById(R.id.rlOK);
        rlOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        return dialog;
    }
}
