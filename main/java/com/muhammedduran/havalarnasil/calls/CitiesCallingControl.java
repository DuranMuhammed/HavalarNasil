package com.muhammedduran.havalarnasil.calls;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import com.muhammedduran.havalarnasil.Retrofit.ApiClient;
import com.muhammedduran.havalarnasil.Retrofit.WeatherAPIAbroad;
import com.muhammedduran.havalarnasil.Retrofit.WeatherAPIAkdeniz;
import com.muhammedduran.havalarnasil.Retrofit.WeatherAPIDoguAnadolu;
import com.muhammedduran.havalarnasil.Retrofit.WeatherAPIEge;
import com.muhammedduran.havalarnasil.Retrofit.WeatherAPIGuneydoguAnadolu;
import com.muhammedduran.havalarnasil.Retrofit.WeatherAPIIcAnadolu;
import com.muhammedduran.havalarnasil.Retrofit.WeatherAPIKaradeniz;
import com.muhammedduran.havalarnasil.Retrofit.WeatherAPIMarmara;
import com.muhammedduran.havalarnasil.adapter.MarqueAkdenizAdapter;
import com.muhammedduran.havalarnasil.adapter.MarqueDoguAnadoluAdapter;
import com.muhammedduran.havalarnasil.adapter.MarqueEgeAdapter;
import com.muhammedduran.havalarnasil.adapter.MarqueGuneydoguAdapter;
import com.muhammedduran.havalarnasil.adapter.MarqueIcAnadoluAdapter;
import com.muhammedduran.havalarnasil.adapter.MarqueKaradenizAdapter;
import com.muhammedduran.havalarnasil.adapter.MarqueMarmaraAdapter;
import com.muhammedduran.havalarnasil.model.citiesLists.CitiesList;
import com.muhammedduran.havalarnasil.model.citiesLists.WeatherModelCities;
import com.muhammedduran.havalarnasil.view.LocationChooseActivity;
import com.muhammedduran.havalarnasil.view.MainActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CitiesCallingControl {

    int weatherAPIOrderControl = 0;
    Call<WeatherModelCities> call;
    Context applicationContext = LocationChooseActivity.getContextOfApplication();
    SharedPreferences sharedPreferences;
    SQLiteDatabase sqLiteDatabase;

    ArrayList<ArrayList<CitiesList>> citieslistArrayList = new ArrayList<>();
    ArrayList<ArrayList<Integer>> citiesListTempArrayList = new ArrayList<>();
    ArrayList<ArrayList<String>> citiesListIconDescArrayList = new ArrayList<>();


    ArrayList<Integer> icAnadoluTempList = new ArrayList<>();
    ArrayList<String> icAnadoluIconDescriptionList = new ArrayList<>();
    ArrayList<Integer> marmaraTempList = new ArrayList<>();
    ArrayList<String> marmaraIconDescriptionList = new ArrayList<>();
    ArrayList<Integer> egeTempList = new ArrayList<>();
    ArrayList<String> egeIconDescriptionList = new ArrayList<>();
    ArrayList<Integer> akdenizTempList = new ArrayList<>();
    ArrayList<String> akdenizIconDescriptionList = new ArrayList<>();
    ArrayList<Integer> karadenizTempList = new ArrayList<>();
    ArrayList<String> karadenizIconDescriptionList = new ArrayList<>();
    ArrayList<Integer> doguAnadoluTempList = new ArrayList<>();
    ArrayList<String> doguAnadoluIconDescriptionList = new ArrayList<>();
    ArrayList<Integer> guneydoguTempList = new ArrayList<>();
    ArrayList<String> guneydoguIconDescriptionList = new ArrayList<>();
    ArrayList<Integer> abroadTempList = new ArrayList<>();
    ArrayList<String> abroadIconDescriptionList = new ArrayList<>();

    WeatherAPIMarmara weatherAPIMarmara = ApiClient.getClient().create(WeatherAPIMarmara.class);
    WeatherAPIIcAnadolu weatherAPIIcAnadolu = ApiClient.getClient().create(WeatherAPIIcAnadolu.class);
    WeatherAPIEge weatherAPIEge = ApiClient.getClient().create(WeatherAPIEge.class);
    WeatherAPIAkdeniz weatherAPIAkdeniz = ApiClient.getClient().create(WeatherAPIAkdeniz.class);
    WeatherAPIKaradeniz weatherAPIKaradeniz = ApiClient.getClient().create(WeatherAPIKaradeniz.class);
    WeatherAPIDoguAnadolu weatherAPIDoguAnadolu = ApiClient.getClient().create(WeatherAPIDoguAnadolu.class);
    WeatherAPIGuneydoguAnadolu weatherAPIGuneydoguAnadolu = ApiClient.getClient().create(WeatherAPIGuneydoguAnadolu.class);
    WeatherAPIAbroad weatherAPIAbroad = ApiClient.getClient().create(WeatherAPIAbroad.class);


    public void createLists() {


        citiesListTempArrayList.add(marmaraTempList);
        citiesListTempArrayList.add(icAnadoluTempList);
        citiesListTempArrayList.add(egeTempList);
        citiesListTempArrayList.add(akdenizTempList);
        citiesListTempArrayList.add(karadenizTempList);
        citiesListTempArrayList.add(doguAnadoluTempList);
        citiesListTempArrayList.add(guneydoguTempList);
        citiesListTempArrayList.add(abroadTempList);

        citiesListIconDescArrayList.add(marmaraIconDescriptionList);
        citiesListIconDescArrayList.add(icAnadoluIconDescriptionList);
        citiesListIconDescArrayList.add(egeIconDescriptionList);
        citiesListIconDescArrayList.add(akdenizIconDescriptionList);
        citiesListIconDescArrayList.add(karadenizIconDescriptionList);
        citiesListIconDescArrayList.add(doguAnadoluIconDescriptionList);
        citiesListIconDescArrayList.add(guneydoguIconDescriptionList);
        citiesListIconDescArrayList.add(abroadIconDescriptionList);


    }

    public void callAllCities(){

            switch (weatherAPIOrderControl){
                case 0:
                    call = weatherAPIMarmara.getWeatherData();
                    //System.out.println(weatherAPIOrderControl + ".SIRA");
                    break;
                case 1:
                    call = weatherAPIIcAnadolu.getWeatherData();
                    //System.out.println(weatherAPIOrderControl + ".SIRA");
                    break;
                case 2:
                    call = weatherAPIEge.getWeatherData();
                    //System.out.println(weatherAPIOrderControl + ".SIRA");
                    break;
                case 3:
                    call = weatherAPIAkdeniz.getWeatherData();
                    //System.out.println(weatherAPIOrderControl + ".SIRA");
                    break;
                case 4:
                    call = weatherAPIKaradeniz.getWeatherData();
                    //System.out.println(weatherAPIOrderControl + ".SIRA");
                    break;
                case 5:
                    call = weatherAPIDoguAnadolu.getWeatherData();
                    //System.out.println(weatherAPIOrderControl + ".SIRA");
                    break;
                case 6:
                    call = weatherAPIGuneydoguAnadolu.getWeatherData();
                    //System.out.println(weatherAPIOrderControl + ".SIRA");
                    break;
                case 7:
                    call = weatherAPIAbroad.getWeatherData();
                    //System.out.println(weatherAPIOrderControl + ".SIRA");
                    break;



            }
        sharedPreferences = applicationContext.getSharedPreferences("com.muhammedduran.havalarnasil", Context.MODE_PRIVATE);
        call.enqueue(new Callback<WeatherModelCities>() {
                @Override
                public void onResponse(Call<WeatherModelCities> call, Response<WeatherModelCities> response) {
                    if(response.isSuccessful()){
                        //System.out.println("Response successful");
                        citieslistArrayList.add((ArrayList<CitiesList>) response.body().list);
                        for(int i=0; i<citieslistArrayList.get(weatherAPIOrderControl).size(); i++){
                            int temp = Math.round(citieslistArrayList.get(weatherAPIOrderControl).get(i).main.temp);
                            //System.out.println("INT TEMP: " + temp);
                            citiesListTempArrayList.get(weatherAPIOrderControl).add(temp);
                            String icon = citieslistArrayList.get(weatherAPIOrderControl).get(i).weather.get(0).icon;
                            citiesListIconDescArrayList.get(weatherAPIOrderControl).add(icon);
                            //System.out.println("TEMP: " + temp + " ICON " + icon);
                            try{
                                sqLiteDatabase = applicationContext.openOrCreateDatabase("Weather", Context.MODE_PRIVATE, null);
                                sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS weatherList (id INTEGER PRIMARY KEY, weathertemplist VARCHAR, weathericonlist VARCHAR)");
                                String sqlString = "INSERT INTO weatherList (weathertemplist, weathericonlist) VALUES (?, ?)";
                                SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sqlString);
                                sqLiteStatement.bindString(1, String.valueOf(temp));
                                sqLiteStatement.bindString(2, icon);
                                sqLiteStatement.execute();
                            }

                            catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                        if(weatherAPIOrderControl != 7){
                            //System.out.println("HALA CALLDAYIM");
                            weatherAPIOrderControl += 1;
                            callAllCities();
                        }
                        else{
                            weatherAPIOrderControl = 0;
                            //System.out.println(citiesListTempArrayList);
                            //System.out.println(citiesListIconDescArrayList);

                            //System.out.println("DEĞER DEĞİŞTİRİLDİ");


                            try{
                                sqLiteDatabase = applicationContext.openOrCreateDatabase("Weather", Context.MODE_PRIVATE, null);
                                sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS weatherControl (id INTEGER PRIMARY KEY, weathercontrol VARCHAR)");
                                String sqlString = "INSERT INTO weatherControl (weathercontrol) VALUES (?)";
                                //String sqlString = "UPDATE weatherControl SET weathercontrol = ? WHERE id = 1";
                                SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sqlString);
                                sqLiteStatement.bindString(1, "FETCH DONE");
                                sqLiteStatement.execute();
                            }

                            catch (Exception e){
                                e.printStackTrace();
                            }
                            if(sharedPreferences.getString("CitiesDataControlString", "NULL").equals("FromMain")){
                                sharedPreferences.edit().putInt("CitiesDataUpdateStatus", 1).apply();
                            }
                            //intent = new Intent(applicationContext, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            //applicationContext.startActivity(intent);

                        }

                    }
                    else{
                        //System.out.println("Respond FAILED");
                    }

                }

                @Override
                public void onFailure(Call<WeatherModelCities> call, Throwable t) {
                    t.printStackTrace();
                    //System.out.println("ON FAILURE");
                }
            });
        }

    public void refreshCitiesData(Context periodicContext){
        ConnectivityManager connectivityManager = (ConnectivityManager) periodicContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getActiveNetworkInfo() != null) {
            if (connectivityManager.getActiveNetworkInfo().isConnected()) {
                try{
                    sqLiteDatabase = periodicContext.openOrCreateDatabase("Weather", Context.MODE_PRIVATE, null);
                    sqLiteDatabase.execSQL("DELETE FROM weatherList");

                    //System.out.println("CITIES TABLE DELETED");

                    sharedPreferences = periodicContext.getSharedPreferences("com.muhammedduran.havalarnasil", Context.MODE_PRIVATE);
                    sharedPreferences.edit().putString("CitiesDataControlString", "FromMain").apply();
                    sharedPreferences.edit().putInt("periodicUpdateControlNumberCities", 1).apply();

                    applicationContext = periodicContext;
                    createLists();
                    callAllCities();

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            else{
                sharedPreferences = periodicContext.getSharedPreferences("com.muhammedduran.havalarnasil", Context.MODE_PRIVATE);
                sharedPreferences.edit().putInt("periodicUpdateControlNumberCities", 0).apply();
            }
        }
        else{
            sharedPreferences = periodicContext.getSharedPreferences("com.muhammedduran.havalarnasil", Context.MODE_PRIVATE);
            sharedPreferences.edit().putInt("periodicUpdateControlNumberCities", 0).apply();
        }
    }

    public void removeCitiesData(){
        try{
            sqLiteDatabase = applicationContext.openOrCreateDatabase("Weather", Context.MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("DELETE FROM weatherList");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

