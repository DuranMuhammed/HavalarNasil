package com.muhammedduran.havalarnasil.model.location;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hourly {

    @SerializedName("dt")
    public String hourlyTime;

    @SerializedName("temp")
    public String hourlyTemp;

    @SerializedName("feels_like")
    public String hourlyFeelslike;

    @SerializedName("pressure")
    public String hourlyPressure;

    @SerializedName("humidity")
    public String hourlyHumidity;

    @SerializedName("wind_speed")
    public String hourlyWindSpeed;

    @SerializedName("wind_deg")
    public String hourlyWindDeg;

    @SerializedName("weather")
    public List<HourlyWeather> hourlyWeathers;

    @SerializedName("pop")
    public String hourlyPop;
}
