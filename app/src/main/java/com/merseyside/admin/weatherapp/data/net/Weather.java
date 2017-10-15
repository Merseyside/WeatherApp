package com.merseyside.admin.weatherapp.data.net;


import com.merseyside.admin.weatherapp.data.entity.Request;
import com.merseyside.admin.weatherapp.data.entity.WeatherResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;



public interface Weather {

    @GET("{request}")
    Call<WeatherResponse> getWeather(@Path("request") Request params, @QueryMap Map<String, String> query);

    @GET("{request}")
    Observable<WeatherResponse> getWeatherObservable(@Path("request") Request params, @QueryMap Map<String, String> query);

}
