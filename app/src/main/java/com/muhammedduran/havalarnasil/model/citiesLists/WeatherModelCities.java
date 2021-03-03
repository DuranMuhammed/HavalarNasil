package com.muhammedduran.havalarnasil.model.citiesLists;

import com.google.gson.annotations.SerializedName;
import com.muhammedduran.havalarnasil.model.citiesLists.CitiesList;

import java.util.List;

public class WeatherModelCities {

    @SerializedName("list")
    public List<CitiesList> list;
}
