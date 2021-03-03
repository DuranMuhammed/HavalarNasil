package com.muhammedduran.havalarnasil.Retrofit;

import com.muhammedduran.havalarnasil.model.location.WeatherModelLocation;
import com.muhammedduran.havalarnasil.model.location.WeatherModelLocationDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPILocationDetails {

    //weather?id={location}&units=metric&lang=tr&appid=14982cf82c1d9a3c3e800933d6cb29d7
    // If there is '/' , @Path is used
    //If there is '?', @Query is used

    @GET("onecall")
        //Call<WeatherModelLocation> getWeatherData();
    Call<WeatherModelLocationDetails> getWeatherData(@Query("lat") String latitude,
                                                     @Query("lon") String longitude,
                                                     @Query("exclude") String exclude,
                                                     @Query("units") String units,
                                                     @Query("lang") String language,
                                                     @Query("appid") String appKey);
}
