package com.muhammedduran.havalarnasil.model.location;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Daily {

    @SerializedName("dt")
    public String dailyTime;

    @SerializedName("sunrise")
    public String dailySunrise;

    @SerializedName("sunset")
    public String dailySunset;

    @SerializedName("temp")
    public DailyTemp dailyTemp;

    @SerializedName("pressure")
    public String dailyPressure;

    @SerializedName("humidity")
    public String dailyHumidity;

    @SerializedName("wind_speed")
    public String dailyWindSpeed;

    @SerializedName("wind_deg")
    public String dailyWindDeg;

    @SerializedName("weather")
    public List<DailyWeather> dailyWeathers;

    @SerializedName("pop")
    public String dailyPop;

    @SerializedName("snow")
    public String dailySnow;
}
