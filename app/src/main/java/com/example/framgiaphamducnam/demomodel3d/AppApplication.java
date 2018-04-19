package com.example.framgiaphamducnam.demomodel3d;

import android.app.Application;
import android.content.Intent;

/**
 * Created by FRAMGIA\pham.duc.nam on 18/04/2018.
 */

public class AppApplication extends Application{
    private static AppApplication INSTANCE;
    private int bottomTabHeight = 168;

    @Override
    public void onCreate(){
        super.onCreate();
        INSTANCE = this;
    }

    public static AppApplication getInstance() {
        return INSTANCE;
    }

    public int getBottomTabHeight() {
        return bottomTabHeight;
    }

    public void setBottomTabHeight(int bottomTabHeight) {
        this.bottomTabHeight = bottomTabHeight;
    }

}
