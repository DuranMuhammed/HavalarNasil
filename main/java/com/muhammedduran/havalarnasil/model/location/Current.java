package com.muhammedduran.havalarnasil.model.location;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Current {

    @SerializedName("dt")
    public String currentTime;

    @SerializedName("sunrise")
    public String sunrise;

    @SerializedName("sunset")
    public String sunset;

    @SerializedName("temp")
    public String temp;

    @SerializedName("feels_like")
    public String feelslike;

    @SerializedName("pressure")
    public String pressure;

    @SerializedName("humidity")
    public String humidity;

    @SerializedName("dew_point")
    public String dewpoint;

    @SerializedName("uvi")
    public String uvi;

    @SerializedName("clouds")
    public String clouds;

    @SerializedName("visibility")
    public String visibility;

    @SerializedName("wind_speed")
    public String windSpeed;

    @SerializedName("wind_deg")
    public String windDeg;

    @SerializedName("weather")
    public List<CurrentWeather> currentWeathers;
}
