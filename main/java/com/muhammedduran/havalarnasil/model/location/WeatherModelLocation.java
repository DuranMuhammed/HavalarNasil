package com.muhammedduran.havalarnasil.model.location;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherModelLocation {

    @SerializedName("coord")
    public Coord coordinate;

    @SerializedName("weather")
    public List<Weather> weatherSimple;

    @SerializedName("main")
    public Main main;

    @SerializedName("visibility")
    public String visibilitySimple;

    @SerializedName("wind")
    public Wind windSimple;

    @SerializedName("dt")
    public String timeSimple;

}
