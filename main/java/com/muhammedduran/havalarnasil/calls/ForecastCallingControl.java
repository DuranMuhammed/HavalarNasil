package com.muhammedduran.havalarnasil.calls;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.net.ConnectivityManager;

import com.muhammedduran.havalarnasil.Retrofit.ApiClient;
import com.muhammedduran.havalarnasil.Retrofit.WeatherAPILocation;
import com.muhammedduran.havalarnasil.Retrofit.WeatherAPILocationDetails;
import com.muhammedduran.havalarnasil.model.location.Current;
import com.muhammedduran.havalarnasil.model.location.Daily;
import com.muhammedduran.havalarnasil.model.location.Hourly;
import com.muhammedduran.havalarnasil.model.location.WeatherModelLocation;
import com.muhammedduran.havalarnasil.model.location.WeatherModelLocationDetails;
import com.muhammedduran.havalarnasil.view.LocationChooseActivity;
import com.muhammedduran.havalarnasil.view.MainActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastCallingControl {

    Call<WeatherModelLocation> call;
    Call<WeatherModelLocationDetails> detailsCall;
    Context applicationContext = LocationChooseActivity.getContextOfApplication();
    SQLiteDatabase sqLiteDatabase;
    SharedPreferences sharedPreferences;
    String latitude;
    String longitude;

    Current currentForecast = new Current();
    ArrayList<Hourly> hourlyForecastArray = new ArrayList<>();
    ArrayList<Daily> dailyForecastArray = new ArrayList<>();
    Intent intent;


    WeatherAPILocation weatherAPILocation = ApiClient.getClient().create(WeatherAPILocation.class);
    WeatherAPILocationDetails weatherAPILocationDetails = ApiClient.getClient().create(WeatherAPILocationDetails.class);

    public void callForecast(String selectedDistrictId){
        try{
            sqLiteDatabase = applicationContext.openOrCreateDatabase("Weather", Context.MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS currentForecastList (id INTEGER PRIMARY KEY, " +
                    "currentTime VARCHAR, sunrise VARCHAR, sunset VARCHAR, currentTemp VARCHAR, " +
                    "feelslike VARCHAR, pressure VARCHAR, humidity VARCHAR, dewpoint VARCHAR, " +
                    "uvi VARCHAR, clouds VARCHAR, visibility VARCHAR, windSpeed VARCHAR, " +
                    "windDeg VARCHAR, currentDescription VARCHAR, currentIcon VARCHAR)");
            //System.out.println("CURRENT TABLO OLUŞTURULDU");
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS hourlyForecastList (id INTEGER PRIMARY KEY, " +
                    "hourlyTime VARCHAR, hourlyTemp VARCHAR, hourlyFeelslike VARCHAR, hourlyPressure VARCHAR, " +
                    "hourlyHumidity VARCHAR, hourlyWindSpeed VARCHAR, hourlyWindDeg VARCHAR, hourlyPop VARCHAR, " +
                    "hourlyDescription VARCHAR, hourlyIcon VARCHAR)");
            //System.out.println("HOURLY TABLO OLUŞTURULDU");
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS dailyForecastList (id INTEGER PRIMARY KEY, " +
                    "dailyTime VARCHAR, dailySunrise VARCHAR, dailySunset VARCHAR, dailyMinTemp VARCHAR, " +
                    "dailyMaxTemp VARCHAR, dailyPressure VARCHAR, dailyHumidity VARCHAR, dailyWindSpeed VARCHAR, " +
                    "dailyWindDeg VARCHAR, dailyPop VARCHAR, dailyDescription VARCHAR, dailyIcon VARCHAR)");
            //System.out.println("DAILY TABLO OLUŞTURULDU");

        }catch(Exception e) {
            e.printStackTrace();
        }
        sharedPreferences = applicationContext.getSharedPreferences("com.muhammedduran.havalarnasil", Context.MODE_PRIVATE);
        call = weatherAPILocation.getWeatherData(selectedDistrictId, "metric", "tr", "14982cf82c1d9a3c3e800933d6cb29d7");
        call.enqueue(new Callback<WeatherModelLocation>() {
            @Override
            public void onResponse(Call<WeatherModelLocation> call, Response<WeatherModelLocation> response) {
                if(response.isSuccessful()){
                    //System.out.println("FIRST CALLLLLLLL"+" RESPONSE SUCCESSFUL");
                    latitude = response.body().coordinate.latitude;
                    //System.out.println(latitude+"ILK");
                    longitude = response.body().coordinate.longitude;
                    //System.out.println(longitude+"ILK");
                    detailsCall = weatherAPILocationDetails.getWeatherData(latitude, longitude, "minutely", "metric", "tr", "14982cf82c1d9a3c3e800933d6cb29d7");
                    detailsCall.enqueue(new Callback<WeatherModelLocationDetails>() {
                        @Override
                        public void onResponse(Call<WeatherModelLocationDetails> call, Response<WeatherModelLocationDetails> response) {
                            //System.out.println(latitude+"IKINCI");
                            //System.out.println(longitude+"IKINCI");
                            if(response.isSuccessful()){
                                //System.out.println("SECOND CALLLLLL"+" RESPONSE SUCCESSFUL");
                                currentForecast = response.body().current;
                                hourlyForecastArray = (ArrayList<Hourly>) response.body().hourlyList;
                                dailyForecastArray = (ArrayList<Daily>) response.body().dailyList;
                                //System.out.println("HOURLY SIZE "+hourlyForecastArray.size());
                                //System.out.println("DAILY SIZE "+dailyForecastArray.size());
                                try{
                                    String sqlString = "INSERT INTO currentForecastList (currentTime, sunrise, sunset, currentTemp, feelslike, " +
                                            "pressure, humidity, dewpoint, uvi, clouds, visibility, windSpeed, windDeg, currentDescription, currentIcon) " +
                                            "VALUES ( ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                                    SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sqlString);
                                    sqLiteStatement.bindString(1, currentForecast.currentTime);
                                    sqLiteStatement.bindString(2, currentForecast.sunrise);
                                    sqLiteStatement.bindString(3, currentForecast.sunset);
                                    sqLiteStatement.bindString(4, currentForecast.temp);
                                    sqLiteStatement.bindString(5, currentForecast.feelslike);
                                    sqLiteStatement.bindString(6, currentForecast.pressure);
                                    sqLiteStatement.bindString(7, currentForecast.humidity);
                                    sqLiteStatement.bindString(8, currentForecast.dewpoint);
                                    sqLiteStatement.bindString(9, currentForecast.uvi);
                                    sqLiteStatement.bindString(10, currentForecast.clouds);
                                    sqLiteStatement.bindString(11, currentForecast.visibility);
                                    sqLiteStatement.bindString(12, currentForecast.windSpeed);
                                    sqLiteStatement.bindString(13, currentForecast.windDeg);
                                    sqLiteStatement.bindString(14, currentForecast.currentWeathers.get(0).currentDescription);
                                    sqLiteStatement.bindString(15, currentForecast.currentWeathers.get(0).currentIcon);
                                    sqLiteStatement.execute();
                                }
                                catch (Exception e){
                                    e.printStackTrace();
                                }
                                for(int i=0; i<hourlyForecastArray.size(); i++){
                                    try{
                                        String sqlString = "INSERT INTO hourlyForecastList (hourlyTime, hourlyTemp, hourlyFeelslike, hourlyPressure, " +
                                                "hourlyHumidity, hourlyWindSpeed, hourlyWindDeg, hourlyPop, hourlyDescription, hourlyIcon) " +
                                                "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                                        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sqlString);
                                        sqLiteStatement.bindString(1,hourlyForecastArray.get(i).hourlyTime);
                                        sqLiteStatement.bindString(2,hourlyForecastArray.get(i).hourlyTemp);
                                        sqLiteStatement.bindString(3,hourlyForecastArray.get(i).hourlyFeelslike);
                                        sqLiteStatement.bindString(4,hourlyForecastArray.get(i).hourlyPressure);
                                        sqLiteStatement.bindString(5,hourlyForecastArray.get(i).hourlyHumidity);
                                        sqLiteStatement.bindString(6,hourlyForecastArray.get(i).hourlyWindSpeed);
                                        sqLiteStatement.bindString(7,hourlyForecastArray.get(i).hourlyWindDeg);
                                        sqLiteStatement.bindString(8,hourlyForecastArray.get(i).hourlyPop);
                                        sqLiteStatement.bindString(9,hourlyForecastArray.get(i).hourlyWeathers.get(0).hourlyDescription);
                                        sqLiteStatement.bindString(10,hourlyForecastArray.get(i).hourlyWeathers.get(0).hourlyIcon);

                                        sqLiteStatement.execute();
                                    }
                                    catch (Exception e){
                                    e.printStackTrace();
                                }}
                                for(int i=0; i<dailyForecastArray.size(); i++){
                                    try{
                                        String sqlString = "INSERT INTO dailyForecastList (dailyTime, dailySunrise, dailySunset, dailyMinTemp, dailyMaxTemp, " +
                                                "dailyPressure, dailyHumidity, dailyWindSpeed, dailyWindDeg, dailyPop, dailyDescription, dailyIcon) " +
                                                "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                                        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sqlString);
                                        sqLiteStatement.bindString(1, dailyForecastArray.get(i).dailyTime);
                                        sqLiteStatement.bindString(2, dailyForecastArray.get(i).dailySunrise);
                                        sqLiteStatement.bindString(3, dailyForecastArray.get(i).dailySunset);
                                        sqLiteStatement.bindString(4, dailyForecastArray.get(i).dailyTemp.dailyMinTemp);
                                        sqLiteStatement.bindString(5, dailyForecastArray.get(i).dailyTemp.dailyMaxTemp);
                                        sqLiteStatement.bindString(6, dailyForecastArray.get(i).dailyPressure);
                                        sqLiteStatement.bindString(7, dailyForecastArray.get(i).dailyHumidity);
                                        sqLiteStatement.bindString(8, dailyForecastArray.get(i).dailyWindSpeed);
                                        sqLiteStatement.bindString(9, dailyForecastArray.get(i).dailyWindDeg);
                                        sqLiteStatement.bindString(10, dailyForecastArray.get(i).dailyPop);
                                        sqLiteStatement.bindString(11, dailyForecastArray.get(i).dailyWeathers.get(0).dailyDescription);
                                        sqLiteStatement.bindString(12, dailyForecastArray.get(i).dailyWeathers.get(0).dailyIcon);

                                        sqLiteStatement.execute();
                                    }
                                    catch (Exception e){
                                    e.printStackTrace();
                                }}
                                if(sharedPreferences.getString("forecastControlString", "FromMain").equals("FromLocation")){
                                    intent = new Intent(applicationContext, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    applicationContext.startActivity(intent);
                                }
                                else{
                                    //FromMain we do NOT intent everytime periodic is launched when mainactivity is created.
                                    sharedPreferences.edit().putInt("forecastDataUpdateStatus", 1).apply();
                                    //intent = new Intent(applicationContext, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    //applicationContext.startActivity(intent);
                                }

                            }
                            else{
                                //System.out.println("Respond FAILED");
                            }

                        }

                        @Override
                        public void onFailure(Call<WeatherModelLocationDetails> call, Throwable t) {
                            t.printStackTrace();
                            //System.out.println("ON FAILURE");
                        }
                    });
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

    public void refreshForecast(String selectedDistrictId, Context periodicContext){

        ConnectivityManager connectivityManager = (ConnectivityManager) periodicContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getActiveNetworkInfo() != null) {
            if (connectivityManager.getActiveNetworkInfo().isConnected()) {
                try{
                    sqLiteDatabase = periodicContext.openOrCreateDatabase("Weather", Context.MODE_PRIVATE, null);
                    sqLiteDatabase.execSQL("DELETE FROM currentForecastList");
                    sqLiteDatabase.execSQL("DELETE FROM hourlyForecastList");
                    sqLiteDatabase.execSQL("DELETE FROM dailyForecastList");

                    //System.out.println("CURRENT FORECAST TABLE DELETED");
                    //System.out.println("HOURLY FORECAST TABLE DELETED");
                    //System.out.println("DAILY FORECAST TABLE DELETED");
                    sharedPreferences = periodicContext.getSharedPreferences("com.muhammedduran.havalarnasil", Context.MODE_PRIVATE);
                    sharedPreferences.edit().putString("forecastControlString", "FromMain").apply();
                    sharedPreferences.edit().putInt("periodicUpdateControlNumberForecast", 1).apply();
                    applicationContext = periodicContext;
                    callForecast(selectedDistrictId);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            else{
                sharedPreferences = periodicContext.getSharedPreferences("com.muhammedduran.havalarnasil", Context.MODE_PRIVATE);
                sharedPreferences.edit().putInt("periodicUpdateControlNumberForecast", 0).apply();
            }

        }
        else{
            sharedPreferences = periodicContext.getSharedPreferences("com.muhammedduran.havalarnasil", Context.MODE_PRIVATE);
            sharedPreferences.edit().putInt("periodicUpdateControlNumberForecast", 0).apply();
        }

    }

    public void removeForecastData(){
        try{
            sqLiteDatabase = applicationContext.openOrCreateDatabase("Weather", Context.MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("DELETE FROM currentForecastList");
            sqLiteDatabase.execSQL("DELETE FROM hourlyForecastList");
            sqLiteDatabase.execSQL("DELETE FROM dailyForecastList");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
