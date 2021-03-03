package com.muhammedduran.havalarnasil.model.location;

import com.google.gson.annotations.SerializedName;

public class HourlyWeather {

    @SerializedName("description")
    public String hourlyDescription;

    @SerializedName("icon")
    public String hourlyIcon;
}
