package com.muhammedduran.havalarnasil.Retrofit;

import com.muhammedduran.havalarnasil.model.citiesLists.WeatherModelCities;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherAPIKaradeniz {

    @GET("group?id=740264,738648,741100,737022,738742,748879,746881,743882,865521,740483,752015,750516,744562,739600,751057,751817,746425,750938&units=metric&lang=tr&appid=14982cf82c1d9a3c3e800933d6cb29d7")
    Call<WeatherModelCities> getWeatherData();
}
