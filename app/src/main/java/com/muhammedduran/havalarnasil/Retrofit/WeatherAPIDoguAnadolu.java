package com.muhammedduran.havalarnasil.Retrofit;

import com.muhammedduran.havalarnasil.model.citiesLists.WeatherModelCities;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherAPIDoguAnadolu {

    @GET("group?id=298117,304922,315368,315807,309647,304081,321025,743952,318137,321082,315373,311665,751952,298846&units=metric&lang=tr&appid=14982cf82c1d9a3c3e800933d6cb29d7")
    Call<WeatherModelCities> getWeatherData();
}
