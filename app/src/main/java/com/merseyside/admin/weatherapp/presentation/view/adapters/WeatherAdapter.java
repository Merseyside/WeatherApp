package com.merseyside.admin.weatherapp.presentation.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.merseyside.admin.weatherapp.R;
import com.merseyside.admin.weatherapp.data.entity.WeatherResponse;
import com.merseyside.admin.weatherapp.presentation.ImageManager;
import com.merseyside.admin.weatherapp.presentation.WeatherApplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ivan_ on 11.10.2017.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyHolder> {

    @Inject
    Context context;

    private ArrayList<WeatherResponse> forecast;
    public WeatherAdapter(ArrayList<WeatherResponse> forecast) {
        WeatherApplication.getApplicationComponent().inject(this);
        this.forecast = forecast;
    }


    class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.temp)
        TextView temp;
        @BindView(R.id.humidity)
        TextView humidity;
        @BindView(R.id.icon)
        ImageView icon;
        @BindView(R.id.date)
        TextView date;

        MyHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_card, parent, false);

        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        WeatherResponse day = forecast.get(position);

        String str = String.valueOf(Math.round(day.getCurrently().getTemperature())) + " \u2103";
        holder.temp.setText(str);

        str = String.valueOf(Math.round(Float.valueOf(day.getCurrently().getHumidity())*100)) + "%";
        holder.humidity.setText(str);

        ImageManager.setIconInImageView(context, holder.icon, day.getCurrently().getIcon());

        Date date = new Date(Long.parseLong(String.valueOf(day.getCurrently().getTime()*1000)));
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        str = sdf.format(date);
        holder.date.setText(str);
    }

    @Override
    public int getItemCount() {
        if (forecast != null) return forecast.size();
        else return 0;
    }

    public ArrayList<WeatherResponse> getData() {
        return forecast;
    }
}
