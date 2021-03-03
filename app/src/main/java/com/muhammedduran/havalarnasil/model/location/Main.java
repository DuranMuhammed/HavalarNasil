package com.muhammedduran.havalarnasil.model.location;

import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    public String tempSimple;

    @SerializedName("feels_like")
    public String feelslikeSimple;

    @SerializedName("pressure")
    public String pressureSimple;

    @SerializedName("humidity")
    public String humiditySimple;
}
