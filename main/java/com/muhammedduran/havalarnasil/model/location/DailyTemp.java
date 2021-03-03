package com.muhammedduran.havalarnasil.model.location;

import com.google.gson.annotations.SerializedName;

public class DailyTemp {

    @SerializedName("min")
    public String dailyMinTemp;

    @SerializedName("max")
    public String dailyMaxTemp;
}
