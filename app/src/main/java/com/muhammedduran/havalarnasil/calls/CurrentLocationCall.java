package com.muhammedduran.havalarnasil.calls;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.net.ConnectivityManager;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.muhammedduran.havalarnasil.Retrofit.ApiClient;
import com.muhammedduran.havalarnasil.Retrofit.WeatherAPILocation;
import com.muhammedduran.havalarnasil.model.location.WeatherModelLocation;
import com.muhammedduran.havalarnasil.view.LocationChooseActivity;
import com.muhammedduran.havalarnasil.view.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentLocationCall {

    Call<WeatherModelLocation> locationCurrentcall;
    WeatherAPILocation weatherAPILocation = ApiClient.getClient().create(WeatherAPILocation.class);
    Context applicationContext = LocationChooseActivity.getContextOfApplication();
    SQLiteDatabase sqLiteDatabase;
    ForecastCallingControl forecastCallingControl;
    SharedPreferences sharedPreferences;
    Intent intent;

    public void callCurrentLocationData(String selectedDistrictId){
        try{
            sqLiteDatabase = applicationContext.openOrCreateDatabase("Weather", Context.MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS currentLocationCallList (id INTEGER PRIMARY KEY, locationDescription VARCHAR, " +
                    "locationIcon VARCHAR, locationTemp VARCHAR, locationFeelsLike VARCHAR, locationPressure VARCHAR, locationHumidity VARCHAR, " +
                    "locationVisibility VARCHAR, locationWindSpeed VARCHAR, locationWindDeg VARCHAR, locationTime VARCHAR)");
            //System.out.println("ANLIK ÇAĞRI TABLOSU OLUŞTURULDU");

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        //System.out.println("CALL CURRENT LOCATION DATA CALLED");
        sharedPreferences = applicationContext.getSharedPreferences("com.muhammedduran.havalarnasil", Context.MODE_PRIVATE);
        locationCurrentcall = weatherAPILocation.getWeatherData(selectedDistrictId, "metric", "tr", "14982cf82c1d9a3c3e800933d6cb29d7");

        locationCurrentcall.enqueue(new Callback<WeatherModelLocation>() {
            @Override
            public void onResponse(Call<WeatherModelLocation> call, Response<WeatherModelLocation> response) {
                forecastCallingControl = new ForecastCallingControl();
                if(response.isSuccessful()){
                    try{
                        String sqlString = "INSERT INTO currentLocationCallList (locationDescription, locationIcon, locationTemp, " +
                                "locationFeelsLike, locationPressure, locationHumidity, locationVisibility, " +
                                "locationWindSpeed, locationWindDeg, locationTime) " +
                                "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sqlString);
                        sqLiteStatement.bindString(1, response.body().weatherSimple.get(0).descriptionSimple);
                        sqLiteStatement.bindString(2, response.body().weatherSimple.get(0).iconSimple);
                        sqLiteStatement.bindString(3, response.body().main.tempSimple);
                        sqLiteStatement.bindString(4, response.body().main.feelslikeSimple);
                        sqLiteStatement.bindString(5, response.body().main.pressureSimple);
                        sqLiteStatement.bindString(6, response.body().main.humiditySimple);
                        sqLiteStatement.bindString(7, response.body().visibilitySimple);
                        sqLiteStatement.bindString(8, response.body().windSimple.speed);
                        sqLiteStatement.bindString(9, response.body().windSimple.deg);
                        sqLiteStatement.bindString(10, response.body().timeSimple);
                        sqLiteStatement.execute();
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }

                    //System.out.println(response.body().timeSimple);
                    long currentLong = Long.parseLong(response.body().timeSimple);
                    // convert seconds to milliseconds
                    Date currentConvertedTime = new java.util.Date(currentLong*1000L);
                    // the format of your date
                    SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm, EEE, dd MMM");
                    // give a timezone reference for formatting (see comment at the bottom)
                    sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+3"));
                    String formattedCurrentTime = sdf.format(currentConvertedTime);
                    //System.out.println("SAVED----"+formattedCurrentTime);
                    //assert response.body() != null;

                    if(sharedPreferences.getString("currentControlString", "FromMain").equals("FromLocation")){
                        //LocationChoose sayfasından çağrıldı
                        forecastCallingControl.callForecast(selectedDistrictId);
                    }
                    else{
                        //MainActivity sayfasından çağrıldı --- GÜNCELLEME
                        sharedPreferences.edit().putInt("currentDataUpdateStatus", 1).apply();
                        intent = new Intent(applicationContext, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        applicationContext.startActivity(intent);


                    }
                }
                else{
                    //System.out.println("Respond FAILED");

                }
            }


            @Override
            public void onFailure(Call<WeatherModelLocation> call, Throwable t) {
                t.printStackTrace();
                //System.out.println("ON FAILURE");
            }
        });
    }

    public void refreshCurrent(String selectedDistrictId){
        //applicationContext = MainActivity.getContextOfApplication();
        ConnectivityManager connectivityManager = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getActiveNetworkInfo() != null) {
            if (connectivityManager.getActiveNetworkInfo().isConnected()) {
                try{
                    sqLiteDatabase = applicationContext.openOrCreateDatabase("Weather", Context.MODE_PRIVATE, null);
                    sqLiteDatabase.execSQL("DELETE FROM currentLocationCallList");

                    //System.out.println("CURRENT TABLE DELETED");
                    sharedPreferences = applicationContext.getSharedPreferences("com.muhammedduran.havalarnasil", Context.MODE_PRIVATE);
                    sharedPreferences.edit().putString("currentControlString", "FromMain").apply();

                    callCurrentLocationData(selectedDistrictId);

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }


    }

    public void removeCurrentLocationData(){

                try{
                    sqLiteDatabase = applicationContext.openOrCreateDatabase("Weather", Context.MODE_PRIVATE, null);
                    sqLiteDatabase.execSQL("DELETE FROM currentLocationCallList");

                }
                catch (Exception e){
                    e.printStackTrace();
                }


    }
}
