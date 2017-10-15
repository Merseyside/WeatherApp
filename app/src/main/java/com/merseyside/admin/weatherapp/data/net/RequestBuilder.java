package com.merseyside.admin.weatherapp.data.net;


public class RequestBuilder {

    private Weather mWeather;

    public RequestBuilder() {
        mWeather = DarkSkyApi.getInstance().getRestAdapter().create(Weather.class);
    }

    public Weather getApi() {
        //mWeather.getWeather(request, request.getQueryParams(), callback);
        return mWeather;
    }
}