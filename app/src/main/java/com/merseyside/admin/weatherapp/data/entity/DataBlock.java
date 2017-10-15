package com.merseyside.admin.weatherapp.data.entity;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ivan_ on 12.10.2017.
 */


public class DataBlock implements Parcelable{

    private String summary;
    private String icon;
    @SerializedName("data") private List<DataPoint> data;

    protected DataBlock(Parcel in) {
        summary = in.readString();
        icon = in.readString();
        data = in.createTypedArrayList(DataPoint.CREATOR);
    }

    public static final Creator<DataBlock> CREATOR = new Creator<DataBlock>() {
        @Override
        public DataBlock createFromParcel(Parcel in) {
            return new DataBlock(in);
        }

        @Override
        public DataBlock[] newArray(int size) {
            return new DataBlock[size];
        }
    };

    public String getSummary() {
        return summary;
    }

    public String getIcon() {
        return icon;
    }

    public List<DataPoint> getData() {
        return data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(summary);
        parcel.writeString(icon);
        parcel.writeTypedList(data);
    }
}
