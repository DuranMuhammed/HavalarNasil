package com.muhammedduran.havalarnasil.Retrofit;

import com.muhammedduran.havalarnasil.model.citiesLists.WeatherModelCities;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherAPIIcAnadolu {

    @GET("group?id=323786,306569,308463,315202,300617,296562,324496,303827,303831,307654,309527,307515,749748&units=metric&lang=tr&appid=14982cf82c1d9a3c3e800933d6cb29d7")
    Call<WeatherModelCities> getWeatherData();

}
