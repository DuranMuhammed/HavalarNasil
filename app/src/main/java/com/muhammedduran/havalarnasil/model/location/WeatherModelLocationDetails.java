package com.muhammedduran.havalarnasil.model.location;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherModelLocationDetails {

    @SerializedName("current")
    public Current current;

    @SerializedName("hourly")
    public List<Hourly> hourlyList;

    @SerializedName("daily")
    public List<Daily> dailyList;


}
