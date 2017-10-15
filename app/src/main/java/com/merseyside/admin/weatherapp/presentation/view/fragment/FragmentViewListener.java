package com.merseyside.admin.weatherapp.presentation.view.fragment;

import com.merseyside.admin.weatherapp.data.entity.WeatherResponse;

import java.util.ArrayList;

/**
 * Created by ivan_ on 11.10.2017.
 */

public interface FragmentViewListener {
    void onForecastReceived(ArrayList<WeatherResponse> forecast);
    void onCurrentWeatherReceived(WeatherResponse weatherResponse);
    void onError(String msg);
}
