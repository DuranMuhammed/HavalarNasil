package com.muhammedduran.havalarnasil.Retrofit;

import com.muhammedduran.havalarnasil.model.location.WeatherModelLocation;
import com.muhammedduran.havalarnasil.view.LocationChooseActivity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherAPILocation {

    //weather?id={location}&units=metric&lang=tr&appid=14982cf82c1d9a3c3e800933d6cb29d7
    // If there is '/' , @Path is used
    //If there is '?', @Query is used

    @GET("weather")
    //Call<WeatherModelLocation> getWeatherData();
    Call<WeatherModelLocation> getWeatherData(@Query("id") String locationId,
                                              @Query("units") String units,
                                              @Query("lang") String language,
                                              @Query("appid") String appKey);




}
