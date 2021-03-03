package com.muhammedduran.havalarnasil.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.muhammedduran.havalarnasil.calls.CitiesCallingControlHelper;
import com.muhammedduran.havalarnasil.calls.CitiesCallingControl;
import com.muhammedduran.havalarnasil.calls.CurrentLocationCall;
import com.muhammedduran.havalarnasil.calls.CurrentLocationCallHelper;
import com.muhammedduran.havalarnasil.calls.ForecastCallingControl;
import com.muhammedduran.havalarnasil.calls.ForecastCallingControlHelper;
import com.muhammedduran.havalarnasil.periodicProcess.PeriodicCitiesRefresh;
import com.muhammedduran.havalarnasil.periodicProcess.PeriodicForecastRefresh;
import com.muhammedduran.havalarnasil.R;
import com.muhammedduran.havalarnasil.adapter.MarqueAbroadAdapter;
import com.muhammedduran.havalarnasil.adapter.MarqueAkdenizAdapter;
import com.muhammedduran.havalarnasil.adapter.MarqueDoguAnadoluAdapter;
import com.muhammedduran.havalarnasil.adapter.MarqueEgeAdapter;
import com.muhammedduran.havalarnasil.adapter.MarqueGuneydoguAdapter;
import com.muhammedduran.havalarnasil.adapter.MarqueIcAnadoluAdapter;
import com.muhammedduran.havalarnasil.adapter.MarqueKaradenizAdapter;
import com.muhammedduran.havalarnasil.adapter.MarqueMarmaraAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> TempFirst;
    ArrayList<String> DescriptionFirst;
    //ArrayList<String> receivedCurrentData;
    public static Context contextOfApplication;
    CitiesCallingControl citiesCallingControl;
    CitiesCallingControlHelper citiesCallingControlHelper;
    CurrentLocationCall currentLocationCall;
    CurrentLocationCallHelper currentLocationCallHelper;
    ForecastCallingControl forecastCallingControl;
    ForecastCallingControlHelper forecastCallingControlHelper;

    SQLiteDatabase database;
    SharedPreferences sharedPreferences;
    PeriodicWorkRequest workRequestForecast;
    PeriodicWorkRequest workRequestCities;

    ImageView imageViewLocation;
    TextView locationTempText, dateText, locationText, locationDescriptionText,
            locationMinMaxText, locationFeelsLikeText;
    ImageView iconToDetail;
    String selectedCity, selectedDistrict, selectedDistrictId;

    RecyclerView recyclerViewMarmara;
    RecyclerView recyclerViewIcAnadolu;
    RecyclerView recyclerViewEge;
    RecyclerView recyclerViewKaradeniz;
    RecyclerView recyclerViewAkdeniz;
    RecyclerView recyclerViewDoguAnadolu;
    RecyclerView recyclerViewGuneydoguAnadolu;
    RecyclerView recyclerViewAbroad;

    LinearLayoutManager layoutManagerMarmara = new LinearLayoutManager(getParent());
    LinearLayoutManager layoutManagerIcAnadolu = new LinearLayoutManager(getParent());
    LinearLayoutManager layoutManagerEge = new LinearLayoutManager(getParent());
    LinearLayoutManager layoutManagerAkdeniz = new LinearLayoutManager(getParent());
    LinearLayoutManager layoutManagerKaradeniz = new LinearLayoutManager(getParent());
    LinearLayoutManager layoutManagerDoguAnadolu = new LinearLayoutManager(getParent());
    LinearLayoutManager layoutManagerGuneydogu = new LinearLayoutManager(getParent());
    LinearLayoutManager layoutManagerAbroad = new LinearLayoutManager(getParent());

    MarqueMarmaraAdapter marqueMarmaraAdapter;
    MarqueIcAnadoluAdapter marqueIcAnadoluAdapter;
    MarqueEgeAdapter marqueEgeAdapter;
    MarqueAkdenizAdapter marqueAkdenizAdapter;
    MarqueKaradenizAdapter marqueKaradenizAdapter;
    MarqueDoguAnadoluAdapter marqueDoguAnadoluAdapter;
    MarqueGuneydoguAdapter marqueGuneydoguAdapter;
    MarqueAbroadAdapter marqueAbroadAdapter;



    private String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?/";

    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Runnable SCROLLING_RUNNABLE = new Runnable() {
        @Override
        public void run() {
            final int duration = 20;
            final int pixelsToMove = 20;


            recyclerViewMarmara.smoothScrollBy(pixelsToMove, 0);
            recyclerViewIcAnadolu.smoothScrollBy(pixelsToMove, 0);
            recyclerViewEge.smoothScrollBy(pixelsToMove, 0);
            recyclerViewAkdeniz.smoothScrollBy(pixelsToMove, 0);
            recyclerViewKaradeniz.smoothScrollBy(pixelsToMove, 0);
            recyclerViewDoguAnadolu.smoothScrollBy(pixelsToMove, 0);
            recyclerViewGuneydoguAnadolu.smoothScrollBy(pixelsToMove, 0);
            recyclerViewAbroad.smoothScrollBy(pixelsToMove, 0);
            mHandler.postDelayed(this, duration);
        }
    };




    public static Context getContextOfApplication() {
        return contextOfApplication;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Hangi menü gösterilecek
        //infaleter xmli aktivite içinde kullanmak için
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.location_change, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.location_change_item){
            ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager.getActiveNetworkInfo() != null) {
                if (connectivityManager.getActiveNetworkInfo().isConnected()) {
                    sharedPreferences = this.getSharedPreferences("com.muhammedduran.havalarnasil", MODE_PRIVATE);
                    sharedPreferences.edit().putInt("Locationselected", 0).apply();
                    currentLocationCall.removeCurrentLocationData();
                    forecastCallingControl.removeForecastData();
                    citiesCallingControl.removeCitiesData();
                    Intent intentToLocationChooseActivity = new Intent(this, LocationChooseActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentToLocationChooseActivity);
                    finish();
                }}

            else{
                Toast.makeText(getApplicationContext(), "Lütfen internet bağlantınızı kontrol edin!",
                        Toast.LENGTH_LONG).show();
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //System.out.println("ON BACK PRESSED");
        this.finishAffinity();
        System.exit(0);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contextOfApplication = getApplicationContext();

        sharedPreferences = this.getSharedPreferences("com.muhammedduran.havalarnasil", MODE_PRIVATE);
        selectedDistrictId = sharedPreferences.getString("selectedDistrictId", "NULL");


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);

        imageViewLocation = findViewById(R.id.imageViewLocation);
        locationText = findViewById(R.id.locationText);
        dateText = findViewById(R.id.dateText);
        locationTempText = findViewById(R.id.locationTempText);
        locationDescriptionText = findViewById(R.id.locationDescriptionText);
        locationMinMaxText = findViewById(R.id.locationMinMaxText);
        locationFeelsLikeText = findViewById(R.id.locationFeelsLikeText);
        iconToDetail = findViewById(R.id.iconToDetail);

        iconToDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDetail();
            }
        });

        Constraints constraints = new Constraints.Builder()
                //.setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        workRequestForecast = new PeriodicWorkRequest.Builder(PeriodicForecastRefresh.class,
                210, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .setInitialDelay(15, TimeUnit.SECONDS)
                .build();

        workRequestCities = new PeriodicWorkRequest.Builder(PeriodicCitiesRefresh.class,
                370, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .setInitialDelay(30, TimeUnit.SECONDS)
                .build();


        citiesCallingControl = new CitiesCallingControl();
        citiesCallingControlHelper = new CitiesCallingControlHelper();
        currentLocationCall = new CurrentLocationCall();
        currentLocationCallHelper = new CurrentLocationCallHelper();
        forecastCallingControl = new ForecastCallingControl();
        forecastCallingControlHelper = new ForecastCallingControlHelper();
        database = this.openOrCreateDatabase("Weather", Context.MODE_PRIVATE, null);

        //RecyclerViewMarmara
        recyclerViewMarmara = (RecyclerView) findViewById(R.id.marqueMarmara);
        layoutManagerMarmara.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewMarmara.setLayoutManager(layoutManagerMarmara);

        //RecyclerViewIcAnadolu
        recyclerViewIcAnadolu = (RecyclerView) findViewById(R.id.marqueIcAnadolu);
        layoutManagerIcAnadolu.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewIcAnadolu.setLayoutManager(layoutManagerIcAnadolu);

        //RecyclerViewEge
        recyclerViewEge = (RecyclerView) findViewById(R.id.marqueEge);
        layoutManagerEge.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewEge.setLayoutManager(layoutManagerEge);

        //RecyclerViewAkdeniz
        recyclerViewAkdeniz = (RecyclerView) findViewById(R.id.marqueAkdeniz);
        layoutManagerAkdeniz.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewAkdeniz.setLayoutManager(layoutManagerAkdeniz);

        //RecyclerViewKaradeniz
        recyclerViewKaradeniz = (RecyclerView) findViewById(R.id.marqueKaradeniz);
        layoutManagerKaradeniz.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewKaradeniz.setLayoutManager(layoutManagerKaradeniz);

        //RecyclerViewDoguAnadolu
        recyclerViewDoguAnadolu = (RecyclerView) findViewById(R.id.marqueDoguAnadolu);
        layoutManagerDoguAnadolu.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewDoguAnadolu.setLayoutManager(layoutManagerDoguAnadolu);

        //RecyclerViewGuneydogu
        recyclerViewGuneydoguAnadolu = (RecyclerView) findViewById(R.id.marqueGuneydogu);
        layoutManagerGuneydogu.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewGuneydoguAnadolu.setLayoutManager(layoutManagerGuneydogu);

        //RecyclerViewAbroad
        recyclerViewAbroad = (RecyclerView) findViewById(R.id.marqueAbroad);
        layoutManagerAbroad.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewAbroad.setLayoutManager(layoutManagerAbroad);


        mHandler.post(SCROLLING_RUNNABLE);


        if(sharedPreferences.getInt("currentDataUpdateStatus", 0) == 1){
            setLocationData();
            setWeatherDataAfterEnque();
            sharedPreferences.edit().putInt("currentDataUpdateStatus", 0).apply();
        }
        else{
            setLocationData();
            setWeatherDataAfterEnque();
            currentLocationCall.refreshCurrent(selectedDistrictId);
        }



    }


    public void setWeatherDataAfterEnque(){
        //System.out.println("SET WEATHER DATA CALLED");

        citiesCallingControlHelper.getCitiesDataFromSQLite();
        //System.out.println("DATA DÜZENLENDİ");

        marqueMarmaraAdapter = new MarqueMarmaraAdapter(citiesCallingControlHelper.getMarmaraTempList(), citiesCallingControlHelper.getMarmaraIconDescriptionList());
        recyclerViewMarmara.setAdapter(marqueMarmaraAdapter);
        //marqueMarmaraAdapter.notifyDataSetChanged();

        marqueIcAnadoluAdapter = new MarqueIcAnadoluAdapter(citiesCallingControlHelper.getIcAnadoluTempList(), citiesCallingControlHelper.getIcAnadoluIconDescriptionList());
        recyclerViewIcAnadolu.setAdapter(marqueIcAnadoluAdapter);

        marqueEgeAdapter = new MarqueEgeAdapter(citiesCallingControlHelper.getEgeTempList(), citiesCallingControlHelper.getEgeIconDescriptionList());
        recyclerViewEge.setAdapter(marqueEgeAdapter);

        marqueAkdenizAdapter = new MarqueAkdenizAdapter(citiesCallingControlHelper.getAkdenizTempList(), citiesCallingControlHelper.getAkdenizIconDescriptionList());
        recyclerViewAkdeniz.setAdapter(marqueAkdenizAdapter);

        marqueKaradenizAdapter = new MarqueKaradenizAdapter(citiesCallingControlHelper.getKaradenizTempList(), citiesCallingControlHelper.getKaradenizIconDescriptionList());
        recyclerViewKaradeniz.setAdapter(marqueKaradenizAdapter);

        marqueDoguAnadoluAdapter = new MarqueDoguAnadoluAdapter(citiesCallingControlHelper.getDoguAnadoluTempList(), citiesCallingControlHelper.getDoguAnadoluIconDescriptionList());
        recyclerViewDoguAnadolu.setAdapter(marqueDoguAnadoluAdapter);

        marqueGuneydoguAdapter = new MarqueGuneydoguAdapter(citiesCallingControlHelper.getGuneydoguTempList(), citiesCallingControlHelper.getGuneydoguIconDescriptionList());
        recyclerViewGuneydoguAnadolu.setAdapter(marqueGuneydoguAdapter);

        marqueAbroadAdapter = new MarqueAbroadAdapter(citiesCallingControlHelper.getAbroadTempList(), citiesCallingControlHelper.getAbroadIconDescriptionList());
        recyclerViewAbroad.setAdapter(marqueAbroadAdapter);

        if(sharedPreferences.getInt("CitiesDataUpdateStatus", 0) !=1){
            WorkManager.getInstance(this).enqueue(workRequestCities);
        }

    }

    public void setLocationData(){
        selectedCity = sharedPreferences.getString("selectedCity", "NULL");
        selectedDistrict = sharedPreferences.getString("selectedDistrict", "NULL");
        selectedDistrictId = sharedPreferences.getString("selectedDistrictId", "NULL");

        //System.out.println("SET LOCATION DATA CALLED");
        currentLocationCallHelper.getCurrentDataFromSQLite();
        //System.out.println("ANLIK DATA DÜZENLENDİ");
        forecastCallingControlHelper.getDetailedForecastDataFromSQLite();
        //System.out.println("FORECAST DATA CALLED");

        if(currentLocationCallHelper.getLocationTime() != null){
            long currentLong = Long.parseLong(currentLocationCallHelper.getLocationTime());
            // convert seconds to milliseconds
            Date currentConvertedTime = new java.util.Date(currentLong*1000L);
            // the format of your date
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm, EEE, dd MMM");
            // give a timezone reference for formatting (see comment at the bottom)
            sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+3"));
            String formattedCurrentTime = sdf.format(currentConvertedTime);
            //System.out.println("GOT-----"+formattedCurrentTime);



            //System.out.println("-----------------------------------------------");
            //System.out.println(currentLocationCallHelper.getLocationTemp());
            //System.out.println(currentLocationCallHelper.getLocationDescription());
            //System.out.println(currentLocationCallHelper.getLocationFeelsLike());
            //System.out.println(forecastCallingControlHelper.getDailyMinTemp().get(0)+"   "+
            //forecastCallingControlHelper.getDailyMaxTemp().get(0));
            //System.out.println("DAILY- TIME SIZE-----" + forecastCallingControlHelper.getDailyTime().size());
            locationText.setText(selectedDistrict+", "+selectedCity);
            dateText.setText(formattedCurrentTime);
            locationTempText.setText(currentLocationCallHelper.getLocationTemp()+"°");
            locationDescriptionText.setText(currentLocationCallHelper.getLocationDescription());
            locationFeelsLikeText.setText("Hissedilen "+currentLocationCallHelper.getLocationFeelsLike()+"°");
            locationMinMaxText.setText(forecastCallingControlHelper.getDailyMinTemp().get(0)+"°"+"/"
                    +forecastCallingControlHelper.getDailyMaxTemp().get(0)+"°");
            switch (currentLocationCallHelper.getLocationIcon()){
                case "01d":
                    imageViewLocation.setImageResource(R.drawable.one_day);
                    break;
                case "01n":
                    imageViewLocation.setImageResource(R.drawable.one_night);
                    break;
                case "02d":
                    imageViewLocation.setImageResource(R.drawable.two_day);
                    break;
                case "02n":
                    imageViewLocation.setImageResource(R.drawable.two_night);
                    break;
                case "03d":
                    imageViewLocation.setImageResource(R.drawable.three_day);
                    break;
                case "03n":
                    imageViewLocation.setImageResource(R.drawable.three_night);
                    break;
                case "04d":
                    imageViewLocation.setImageResource(R.drawable.four_day);
                    break;
                case "04n":
                    imageViewLocation.setImageResource(R.drawable.four_night);
                    break;
                case "09d":
                    imageViewLocation.setImageResource(R.drawable.nine_day);
                    break;
                case "09n":
                    imageViewLocation.setImageResource(R.drawable.nine_night);
                    break;
                case "10d":
                    imageViewLocation.setImageResource(R.drawable.ten_day);
                    break;
                case "10n":
                    imageViewLocation.setImageResource(R.drawable.ten_night);
                    break;
                case "11d":
                    imageViewLocation.setImageResource(R.drawable.eleven_day);
                    break;
                case "11n":
                    imageViewLocation.setImageResource(R.drawable.eleven_night);
                    break;
                case "13d":
                    imageViewLocation.setImageResource(R.drawable.thirteen_day);
                    break;
                case "13n":
                    imageViewLocation.setImageResource(R.drawable.thirteen_night);
                    break;
                case "50d":
                    imageViewLocation.setImageResource(R.drawable.fifty_day);
                    break;
                case "50n":
                    imageViewLocation.setImageResource(R.drawable.fifty_night);
                    break;
            }
        }




        if(sharedPreferences.getInt("forecastDataUpdateStatus", 0) != 1){
            WorkManager.getInstance(this).enqueue(workRequestForecast);
        }

    }

    public void toDetail(){
        Intent intenttoDetails = new Intent(this, DetailsForecastActivity.class);
        startActivity(intenttoDetails);
    }

}