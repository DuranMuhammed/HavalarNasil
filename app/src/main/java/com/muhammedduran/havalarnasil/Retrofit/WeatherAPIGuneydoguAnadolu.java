package com.muhammedduran.havalarnasil.Retrofit;

import com.muhammedduran.havalarnasil.model.citiesLists.WeatherModelCities;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherAPIGuneydoguAnadolu {

    @GET("group?id=314830,298333,316541,304797,325329,321836,300640,300822,307864&units=metric&lang=tr&appid=14982cf82c1d9a3c3e800933d6cb29d7")
    Call<WeatherModelCities> getWeatherData();
}
