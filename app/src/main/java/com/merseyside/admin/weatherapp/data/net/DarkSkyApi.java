package com.merseyside.admin.weatherapp.data.net;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ivan_ on 11.10.2017.
 */

public class DarkSkyApi {

    private static DarkSkyApi mInstance = null;
    private Retrofit retrofit;
    private static final String BASE_API_URL = "https://api.darksky.net/forecast/";

    public static DarkSkyApi getInstance() {
        return mInstance;
    }

    public Retrofit getRestAdapter() {
        return retrofit;
    }

    public static void create(String apiKey) {
        mInstance = new DarkSkyApi(apiKey);
    }

    private DarkSkyApi(String apiKey) {
        String apiUrl = BASE_API_URL + apiKey;
        retrofit = new Retrofit.Builder()
                .baseUrl(apiUrl+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
