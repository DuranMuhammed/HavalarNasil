package com.muhammedduran.havalarnasil.model.location;

import com.google.gson.annotations.SerializedName;

public class CurrentWeather {

    @SerializedName("description")
    public String currentDescription;

    @SerializedName("icon")
    public String currentIcon;

}
