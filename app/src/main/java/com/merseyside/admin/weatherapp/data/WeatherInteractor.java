package com.merseyside.admin.weatherapp.data;

import com.merseyside.admin.weatherapp.data.entity.WeatherResponse;

import java.util.ArrayList;


/**
 * Created by ivan_ on 11.10.2017.
 */

public interface WeatherInteractor {
    void getWeatherForWeek();
    void getCurrentWeather();

    interface OnFinishedResult {
        void onForecastReceived(ArrayList<WeatherResponse> forecast);
        void onCurrentWeatherReceived(WeatherResponse response);
        void onError(String msg);
    }
}
