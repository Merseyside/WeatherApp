package com.merseyside.admin.weatherapp.presentation.view.fragment;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.merseyside.admin.weatherapp.R;
import com.merseyside.admin.weatherapp.data.entity.WeatherResponse;
import com.merseyside.admin.weatherapp.presentation.ImageManager;
import com.merseyside.admin.weatherapp.presentation.WeatherApplication;
import com.merseyside.admin.weatherapp.presentation.presenter.WeatherFragmentPresenter;
import com.merseyside.admin.weatherapp.presentation.view.activity.MainViewListener;
import com.merseyside.admin.weatherapp.presentation.view.adapters.WeatherAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ivan_ on 11.10.2017.
 */

public class WeatherFragment extends BaseFragment implements FragmentViewListener{

    @Inject
    Context context;
    private MainViewListener mMainViewListener;
    private WeatherFragmentPresenter mPresenter;
    private WeatherResponse current;
    WeatherAdapter adapter;

    @BindView(R.id.weather_recycler_view)
    RecyclerView weatherWidget;

    @BindView(R.id.main_icon)
    ImageView mainIcon;

    @BindView(R.id.summary)
    TextView summary;

    @BindView(R.id.location)
    TextView location;

    @BindView(R.id.temp)
    TextView temperature;

    @BindView(R.id.humidity)
    TextView humidity;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    @BindView(R.id.layout)
    ConstraintLayout layout;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity)context;
        try {
            mMainViewListener = (MainViewListener) activity;
        } catch(ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement mainViewListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WeatherApplication.getApplicationComponent().inject(this);
        mPresenter = new WeatherFragmentPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.weather_widget, null);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupWeatherWidget();
        if (savedInstanceState == null) getWeather();
        else {
            current = savedInstanceState.getParcelable("current");
            ArrayList<WeatherResponse> responces = savedInstanceState.getParcelableArrayList("week");
            setAdapter(responces);
            showCurrentWeather(current);
        }

    }

    private void setupWeatherWidget() {

        RecyclerView.LayoutManager recyclerViewLayoutManager = new LinearLayoutManager(context.getApplicationContext());
        weatherWidget.setLayoutManager(recyclerViewLayoutManager);
        weatherWidget.setHasFixedSize(true);
        weatherWidget.setItemViewCacheSize(20);
        weatherWidget.setDrawingCacheEnabled(true);
        weatherWidget.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

    private void setAdapter(ArrayList<WeatherResponse> forecast) {
        adapter = new WeatherAdapter(forecast);

        LinearLayoutManager horizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        weatherWidget.setLayoutManager(horizontalLayout);
        weatherWidget.setAdapter(adapter);
    }

    public void getWeather() {
        showProgressBar();
        mPresenter.getCurrentWeather();
        mPresenter.getWeatherForWeek();
    }

    @Override
    public void onForecastReceived(ArrayList<WeatherResponse> forecast) {
        setAdapter(forecast);
        hideProgressBar();
        mMainViewListener.onComplete();
    }

    private void showCurrentWeather(WeatherResponse response) {
        ImageManager.setIconInImageView(context, mainIcon, response.getCurrently().getIcon());
        String str = response.getCurrently().getSummary();
        summary.setText(str);
        str = String.valueOf(response.getCurrently().getTemperature()) + " \u2103";
        temperature.setText(str);
        str = response.getTimezone();
        location.setText(str);
        str = String.valueOf(Math.round(Float.valueOf(response.getCurrently().getHumidity())*100)) + "%";
        humidity.setText(str);
        setFragmentBackground(response.getCurrently().getIcon());
    }

    public void onSaveInstanceState(Bundle outState) {
        if (adapter != null && adapter.getData() != null) outState.putParcelableArrayList("week", adapter.getData());
        if (current != null) outState.putParcelable("current", current);
        super.onSaveInstanceState(outState);
    }

    private void showProgressBar()  {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void setFragmentBackground(String icon) {
        ImageManager.setViewBackground(layout, icon, true);
    }

    @Override
    public void onCurrentWeatherReceived(WeatherResponse weatherResponse) {
        current = weatherResponse;
        showCurrentWeather(current);
    }

    @Override
    public void onError(String msg) {
        hideProgressBar();
        mMainViewListener.onError(msg);
    }
}
