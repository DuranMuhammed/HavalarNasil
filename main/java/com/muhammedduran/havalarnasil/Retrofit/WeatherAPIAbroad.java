package com.muhammedduran.havalarnasil.Retrofit;

import com.muhammedduran.havalarnasil.model.citiesLists.WeatherModelCities;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherAPIAbroad {

    @GET("group?id=104515,109223,281184,1517945,1516393,1280849,587084,1512569,162183,1216265,1217662,147774,3191281,693805,94787,1162015&units=metric&lang=tr&appid=14982cf82c1d9a3c3e800933d6cb29d7")
    Call<WeatherModelCities> getWeatherData();
}
