package com.merseyside.admin.weatherapp.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by ivan_ on 12.10.2017.
 */

public class WeatherResponse implements Parcelable{

    private double latitude;
    private double longitude;
    private String timezone;
    private String offset;
    private DataPoint currently;
    private DataBlock minutely;
    private DataBlock hourly;
    private DataBlock daily;
    private List<AlertsBlock> alerts;

    protected WeatherResponse(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
        timezone = in.readString();
        offset = in.readString();
    }

    public static final Creator<WeatherResponse> CREATOR = new Creator<WeatherResponse>() {
        @Override
        public WeatherResponse createFromParcel(Parcel in) {
            return new WeatherResponse(in);
        }

        @Override
        public WeatherResponse[] newArray(int size) {
            return new WeatherResponse[size];
        }
    };

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getOffset() {
        return offset;
    }

    public DataPoint getCurrently() {
        return currently;
    }

    public DataBlock getMinutely() {
        return minutely;
    }

    public DataBlock getHourly() {
        return hourly;
    }

    public DataBlock getDaily() {
        return daily;
    }

    public List<AlertsBlock> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<AlertsBlock> alerts) {
        this.alerts = alerts;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeString(timezone);
        parcel.writeString(offset);
        parcel.writeParcelable(currently, PARCELABLE_WRITE_RETURN_VALUE);
    }
}
