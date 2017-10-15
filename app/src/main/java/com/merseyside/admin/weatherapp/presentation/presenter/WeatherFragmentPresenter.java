package com.merseyside.admin.weatherapp.presentation.presenter;

import com.merseyside.admin.weatherapp.data.WeatherInteractor;
import com.merseyside.admin.weatherapp.data.WeatherInteractorImpl;
import com.merseyside.admin.weatherapp.data.entity.WeatherResponse;
import com.merseyside.admin.weatherapp.presentation.view.fragment.FragmentViewListener;

import java.util.ArrayList;

/**
 * Created by ivan_ on 11.10.2017.
 */

public class WeatherFragmentPresenter implements WeatherInteractor.OnFinishedResult{
    private FragmentViewListener mListener;
    private WeatherInteractor mWeatherInteractor;


    public WeatherFragmentPresenter(FragmentViewListener listener) {
        this.mListener = listener;
        mWeatherInteractor = new WeatherInteractorImpl(this);
    }

    public void getCurrentWeather() {
        mWeatherInteractor.getCurrentWeather();
    }


    public void getWeatherForWeek() {
        mWeatherInteractor.getWeatherForWeek();
    }


    @Override
    public void onForecastReceived(ArrayList<WeatherResponse> forecast) {
        mListener.onForecastReceived(forecast);
    }

    @Override
    public void onCurrentWeatherReceived(WeatherResponse response) {
        mListener.onCurrentWeatherReceived(response);
    }

    @Override
    public void onError(String msg) {
        mListener.onError(msg);
    }
}
