package com.merseyside.admin.weatherapp.presentation.navigation;

import android.content.Context;

import com.merseyside.admin.weatherapp.presentation.WeatherApplication;

/**
 * Created by ivan_ on 09.10.2017.
 */

public class Navigator {

    private Context mContext;

    public Navigator(Context context) {
        mContext = context;
    }

    public void exit() {
        WeatherApplication.close();
    }
}
