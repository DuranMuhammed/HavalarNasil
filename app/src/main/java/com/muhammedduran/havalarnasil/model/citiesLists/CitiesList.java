package com.muhammedduran.havalarnasil.model.citiesLists;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CitiesList {

    @SerializedName("main")
    public MainList main;

    @SerializedName("weather")
    public List<WeatherDescriptionList> weather;

}
