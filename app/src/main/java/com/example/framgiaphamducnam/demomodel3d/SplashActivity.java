package com.example.framgiaphamducnam.demomodel3d;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by FRAMGIA\pham.duc.nam on 18/04/2018.
 */

public class SplashActivity extends AppCompatActivity {
    private final int TIME_LOADING = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }, TIME_LOADING);

    }
}
