package com.merseyside.admin.weatherapp.data.entity;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ivan_ on 12.10.2017.
 */

public class DataPoint implements Parcelable{

    private long time;
    private String summary;
    private String icon;
    private String sunriseTime;
    private String sunsetTime;
    private String moonPhase;
    private String nearestStormDistance;
    private String nearestStormBearing;
    private String precipIntensity;
    private String precipIntensityMax;
    private String precipIntensityMaxTime;
    private String precipProbability;
    private String precipType;
    private String precipAccumulation;
    private double temperature;
    private double temperatureMin;
    private double temperatureMinTime;
    private double temperatureMax;
    private double temperatureMaxTime;
    private double apparentTemperature;
    private double apparentTemperatureMin;
    private double apparentTemperatureMinTime;
    private double apparentTemperatureMax;
    private double apparentTemperatureMaxTime;
    private String dewPoint;
    private String windSpeed;
    private String windBearing;
    private String cloudCover;
    private String humidity;
    private String pressure;
    private String visibility;
    private String ozone;

    protected DataPoint(Parcel in) {
        time = in.readLong();
        summary = in.readString();
        icon = in.readString();
        sunriseTime = in.readString();
        sunsetTime = in.readString();
        moonPhase = in.readString();
        nearestStormDistance = in.readString();
        nearestStormBearing = in.readString();
        precipIntensity = in.readString();
        precipIntensityMax = in.readString();
        precipIntensityMaxTime = in.readString();
        precipProbability = in.readString();
        precipType = in.readString();
        precipAccumulation = in.readString();
        temperature = in.readDouble();
        temperatureMin = in.readDouble();
        temperatureMinTime = in.readDouble();
        temperatureMax = in.readDouble();
        temperatureMaxTime = in.readDouble();
        apparentTemperature = in.readDouble();
        apparentTemperatureMin = in.readDouble();
        apparentTemperatureMinTime = in.readDouble();
        apparentTemperatureMax = in.readDouble();
        apparentTemperatureMaxTime = in.readDouble();
        dewPoint = in.readString();
        windSpeed = in.readString();
        windBearing = in.readString();
        cloudCover = in.readString();
        humidity = in.readString();
        pressure = in.readString();
        visibility = in.readString();
        ozone = in.readString();
    }

    public static final Creator<DataPoint> CREATOR = new Creator<DataPoint>() {
        @Override
        public DataPoint createFromParcel(Parcel in) {
            return new DataPoint(in);
        }

        @Override
        public DataPoint[] newArray(int size) {
            return new DataPoint[size];
        }
    };

    public long getTime() {
        return time;
    }

    public String getSummary() {
        return summary;
    }

    public String getIcon() {
        return icon;
    }

    public String getSunriseTime() {
        return sunriseTime;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }

    public String getMoonPhase() {
        return moonPhase;
    }

    public String getNearestStormDistance() {
        return nearestStormDistance;
    }

    public String getNearestStormBearing() {
        return nearestStormBearing;
    }

    public String getPrecipIntensity() {
        return precipIntensity;
    }

    public String getPrecipIntensityMax() {
        return precipIntensityMax;
    }

    public String getPrecipIntensityMaxTime() {
        return precipIntensityMaxTime;
    }

    public String getPrecipProbability() {
        return precipProbability;
    }

    public String getPrecipType() {
        return precipType;
    }

    public String getPrecipAccumulation() {
        return precipAccumulation;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }

    public double getTemperatureMinTime() {
        return temperatureMinTime;
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

    public double getTemperatureMaxTime() {
        return temperatureMaxTime;
    }

    public double getApparentTemperature() {
        return apparentTemperature;
    }

    public double getApparentTemperatureMin() {
        return apparentTemperatureMin;
    }

    public double getApparentTemperatureMinTime() {
        return apparentTemperatureMinTime;
    }

    public double getApparentTemperatureMax() {
        return apparentTemperatureMax;
    }

    public double getApparentTemperatureMaxTime() {
        return apparentTemperatureMaxTime;
    }

    public String getDewPoint() {
        return dewPoint;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getWindBearing() {
        return windBearing;
    }

    public String getCloudClover() {
        return cloudCover;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getOzone() {
        return ozone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(time);
        parcel.writeString(summary);
        parcel.writeString(icon);
        parcel.writeString(sunriseTime);
        parcel.writeString(sunsetTime);
        parcel.writeString(moonPhase);
        parcel.writeString(nearestStormDistance);
        parcel.writeString(nearestStormBearing);
        parcel.writeString(precipIntensity);
        parcel.writeString(precipIntensityMax);
        parcel.writeString(precipIntensityMaxTime);
        parcel.writeString(precipProbability);
        parcel.writeString(precipType);
        parcel.writeString(precipAccumulation);
        parcel.writeDouble(temperature);
        parcel.writeDouble(temperatureMin);
        parcel.writeDouble(temperatureMinTime);
        parcel.writeDouble(temperatureMax);
        parcel.writeDouble(temperatureMaxTime);
        parcel.writeDouble(apparentTemperature);
        parcel.writeDouble(apparentTemperatureMin);
        parcel.writeDouble(apparentTemperatureMinTime);
        parcel.writeDouble(apparentTemperatureMax);
        parcel.writeDouble(apparentTemperatureMaxTime);
        parcel.writeString(dewPoint);
        parcel.writeString(windSpeed);
        parcel.writeString(windBearing);
        parcel.writeString(cloudCover);
        parcel.writeString(humidity);
        parcel.writeString(pressure);
        parcel.writeString(visibility);
        parcel.writeString(ozone);
    }
}
