package com.muhammedduran.havalarnasil.Retrofit;

import com.muhammedduran.havalarnasil.model.citiesLists.WeatherModelCities;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherAPIEge {

    @GET("group?id=311044,304825,322830,317109,304184,325303,305268,298299&units=metric&lang=tr&appid=14982cf82c1d9a3c3e800933d6cb29d7")
    Call<WeatherModelCities> getWeatherData();
}
