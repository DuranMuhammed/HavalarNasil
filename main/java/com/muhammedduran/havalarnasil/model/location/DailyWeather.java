package com.muhammedduran.havalarnasil.model.location;

import com.google.gson.annotations.SerializedName;

public class DailyWeather {

    @SerializedName("description")
    public String dailyDescription;

    @SerializedName("icon")
    public String dailyIcon;
}
