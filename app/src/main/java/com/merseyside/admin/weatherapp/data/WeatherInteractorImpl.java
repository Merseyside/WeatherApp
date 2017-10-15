package com.merseyside.admin.weatherapp.data;

import android.content.Context;
import android.util.Log;

import com.merseyside.admin.weatherapp.R;
import com.merseyside.admin.weatherapp.data.entity.Request;
import com.merseyside.admin.weatherapp.data.entity.WeatherResponse;
import com.merseyside.admin.weatherapp.data.exception.ErrorMessageFactory;
import com.merseyside.admin.weatherapp.data.net.RequestBuilder;
import com.merseyside.admin.weatherapp.data.net.Weather;
import com.merseyside.admin.weatherapp.presentation.WeatherApplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by ivan_ on 11.10.2017.
 */

public class WeatherInteractorImpl implements WeatherInteractor {
    @Inject
    Context context;

    final String TAG = "tag";
    private RequestBuilder builder;
    private Weather weather;


    private WeatherInteractor.OnFinishedResult mListener;

    public WeatherInteractorImpl(WeatherInteractor.OnFinishedResult listener) {
        WeatherApplication.getApplicationComponent().inject(this);
        this.mListener = listener;

        builder = new RequestBuilder();
        weather = builder.getApi();
    }

    @Override
    public void getWeatherForWeek() {
        if (!MyUtils.isOnline(context)) {
            mListener.onError(context.getString(R.string.no_internet));
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss", Locale.US);

        Calendar calendar = Calendar.getInstance();
        Date date;

        String time;
        List<Observable<?>> requests = new ArrayList<>();

        Request request;

        for (int i = 0; i < 14; i++) {
            date = calendar.getTime();
            time = sdf.format(date);
            request = getRequest(time);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            requests.add(weather.getWeatherObservable(request, request.getQueryParams()));
        }

        Observable.zip(
                requests,
                objects -> {
                    ArrayList<WeatherResponse> responces = new ArrayList<>();
                    for (Object obj : objects) {
                        WeatherResponse response = (WeatherResponse) obj;
                        responces.add(response);
                    }
                    return responces;
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    @Override
    public void getCurrentWeather() {
        if (!MyUtils.isOnline(context)) {
            mListener.onError(context.getString(R.string.no_internet));
            return;
        }
        Request request = getRequest(null);

        builder.getApi().getWeather(request, request.getQueryParams()).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                mListener.onCurrentWeatherReceived(response.body());
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                mListener.onError(ErrorMessageFactory.create(t));
            }
        });
    }

    private Observer<ArrayList<WeatherResponse>> getObserver() {
        return new Observer<ArrayList<WeatherResponse>>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(ArrayList<WeatherResponse> forecast) {
                mListener.onForecastReceived(forecast);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, " onComplete");
            }
        };
    }

    private Request getRequest(String time) {
        Request request = new Request();
        request.setLat("55.00");
        request.setLng("82.00");
        if (time != null) request.setTime(time);
        request.setUnits(Request.Units.AUTO);
        request.setLanguage(Request.Language.RUSSIAN);
        request.addExcludeBlock(Request.Block.CURRENTLY);
        request.removeExcludeBlock(Request.Block.CURRENTLY);
        return request;

    }
}
