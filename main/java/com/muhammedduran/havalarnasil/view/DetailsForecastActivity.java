package com.muhammedduran.havalarnasil.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.muhammedduran.havalarnasil.calls.CitiesCallingControl;
import com.muhammedduran.havalarnasil.calls.CurrentLocationCallHelper;
import com.muhammedduran.havalarnasil.calls.ForecastCallingControl;
import com.muhammedduran.havalarnasil.calls.ForecastCallingControlHelper;
import com.muhammedduran.havalarnasil.R;
import com.muhammedduran.havalarnasil.adapter.CustomListAdapter;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DetailsForecastActivity extends AppCompatActivity {

    ArrayList<TextView> hourlyTimeList, hourlyTempList, hourlyRainChanceList;
    ArrayList<TextView> dailyTimeList, dailyTempList, dailyRainChanceList;
    ArrayList<ImageView> hourlyIconList, dailyIconList;
    ArrayList<LinearLayout> hourlyLinearLayout, dailyLinearLayout;


    ListView currentListView;
    String propertiesList[] = {"Rüzgâr Durumu", "Basınç", "Nem", "Görüş Mesafesi"};
    String valuesList[] = new String[4];
    String selectedDistrictId;
    CustomListAdapter customListAdapter;

    SwipeRefreshLayout swipeRefreshLayout;
    SharedPreferences sharedPreferences;

    CurrentLocationCallHelper currentLocationCallHelper;
    ForecastCallingControl forecastCallingControl;
    CitiesCallingControl citiesCallingControl;
    ForecastCallingControlHelper forecastCallingControlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_forecast);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);

        forecastCallingControl = new ForecastCallingControl();
        citiesCallingControl = new CitiesCallingControl();

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        sharedPreferences = this.getSharedPreferences("com.muhammedduran.havalarnasil", MODE_PRIVATE);

        if(sharedPreferences.getInt("periodicUpdateControlNumberForecast", 1) != 1
        || sharedPreferences.getInt("periodicUpdateControlNumberCities", 1) != 1){
            if(sharedPreferences.getInt("periodicUpdateControlNumberForecast", 1) != 1){
                selectedDistrictId = sharedPreferences.getString("selectedDistrictId", "NULL");
                AlertDialog.Builder alert = new AlertDialog.Builder(DetailsForecastActivity.this);

                alert.setMessage("Tahmin verileri internet bağlantısı olmadığından dolayı güncellenemdi.\n" +
                        "Verileri güncellemek için internet bağlantınız olduğundan emin olduktan sonra sayfayı yukarıdan aşağıya " +
                        "doğru kaydırınız.");

                alert.show();

                ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getActiveNetworkInfo() != null) {
                    if (connectivityManager.getActiveNetworkInfo().isConnected()) {
                        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                            @Override
                            public void onRefresh() {
                                //System.out.println("onRefresh called from SwipeRefreshLayout");
                                swipeRefreshLayout.setRefreshing(false);
                                forecastCallingControl.refreshForecast(selectedDistrictId, DetailsForecastActivity.this);
                                //citiesCallingControl.refreshCitiesData(DetailsForecastActivity.this);

                            }
                        });
                    }
                }
                else{
                    swipeRefreshLayout.setRefreshing(false);
                    swipeRefreshLayout.setEnabled(false);
                }
            }
            else if(sharedPreferences.getInt("periodicUpdateControlNumberCities", 1) != 1){
                AlertDialog.Builder alert = new AlertDialog.Builder(DetailsForecastActivity.this);

                alert.setMessage("Bölge verileri internet bağlantısı olmadığından dolayı güncellenemdi.\n" +
                        "Verileri güncellemek için internet bağlantınız olduğundan emin olduktan sonra sayfayı yukarıdan aşağıya " +
                        "doğru kaydırınız.");

                alert.show();

                ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getActiveNetworkInfo() != null) {
                    if (connectivityManager.getActiveNetworkInfo().isConnected()) {
                        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                            @Override
                            public void onRefresh() {
                                //System.out.println("onRefresh called from SwipeRefreshLayout");
                                swipeRefreshLayout.setRefreshing(false);
                                //forecastCallingControl.refreshForecast(selectedDistrictId, DetailsForecastActivity.this);
                                citiesCallingControl.refreshCitiesData(DetailsForecastActivity.this);

                            }
                        });
                    }
                }
                else{
                    swipeRefreshLayout.setRefreshing(false);
                    swipeRefreshLayout.setEnabled(false);
                }
            }




        }
        else{
            swipeRefreshLayout.setRefreshing(false);
            swipeRefreshLayout.setEnabled(false);
        }


        currentListView = findViewById(R.id.currentDetailsList);
        customListAdapter = new CustomListAdapter(getApplicationContext(), propertiesList, valuesList);
        currentListView.setAdapter(customListAdapter);

        currentLocationCallHelper = new CurrentLocationCallHelper();
        forecastCallingControlHelper = new ForecastCallingControlHelper();




        setLinearLayoutsWidth();
        setCurrentListViewData();
        setHourlyScrollViewData();
        setDailyScrollViewData();

    }


    public void setLinearLayoutsWidth(){
        ViewGroup.LayoutParams paramsHour;
        ViewGroup.LayoutParams paramsDay;

        hourlyLinearLayout = new ArrayList<>();
        dailyLinearLayout = new ArrayList<>();

        hourlyLinearLayout.add(findViewById(R.id.firstHourLinearLayout));
        hourlyLinearLayout.add(findViewById(R.id.secondHourLinearLayout));
        hourlyLinearLayout.add(findViewById(R.id.thirdHourLinearLayout));
        hourlyLinearLayout.add(findViewById(R.id.fourthHourLinearLayout));
        hourlyLinearLayout.add(findViewById(R.id.fifthHourLinearLayout));
        hourlyLinearLayout.add(findViewById(R.id.sixthHourLinearLayout));
        hourlyLinearLayout.add(findViewById(R.id.seventhHourLinearLayout));
        hourlyLinearLayout.add(findViewById(R.id.eighthHourLinearLayout));
        hourlyLinearLayout.add(findViewById(R.id.ninthHourLinearLayout));
        hourlyLinearLayout.add(findViewById(R.id.tenthHourLinearLayout));
        hourlyLinearLayout.add(findViewById(R.id.eleventhHourLinearLayout));
        hourlyLinearLayout.add(findViewById(R.id.twelfthHourLinearLayout));
        hourlyLinearLayout.add(findViewById(R.id.thirteenthHourLinearLayout));
        hourlyLinearLayout.add(findViewById(R.id.fourteenthHourLinearLayout));
        hourlyLinearLayout.add(findViewById(R.id.fifteenthHourLinearLayout));
        hourlyLinearLayout.add(findViewById(R.id.sixteenthHourLinearLayout));
        hourlyLinearLayout.add(findViewById(R.id.seventeenthHourLinearLayout));
        hourlyLinearLayout.add(findViewById(R.id.eighteenthHourLinearLayout));
        hourlyLinearLayout.add(findViewById(R.id.nineteenthHourLinearLayout));
        hourlyLinearLayout.add(findViewById(R.id.twentiethHourLinearLayout));
        hourlyLinearLayout.add(findViewById(R.id.twentyfirstHourLinearLayout));
        hourlyLinearLayout.add(findViewById(R.id.twentysecondHourLinearLayout));
        hourlyLinearLayout.add(findViewById(R.id.twentythirdHourLinearLayout));
        hourlyLinearLayout.add(findViewById(R.id.twentyfourthHourLinearLayout));

        dailyLinearLayout.add(findViewById(R.id.firstDailyLinearLayout));
        dailyLinearLayout.add(findViewById(R.id.secondDailyLinearLayout));
        dailyLinearLayout.add(findViewById(R.id.thirdDailyLinearLayout));
        dailyLinearLayout.add(findViewById(R.id.fourthDailyLinearLayout));
        dailyLinearLayout.add(findViewById(R.id.fifthDailyLinearLayout));
        dailyLinearLayout.add(findViewById(R.id.sixthDailyLinearLayout));
        dailyLinearLayout.add(findViewById(R.id.seventhDailyLinearLayout));
        dailyLinearLayout.add(findViewById(R.id.eighthDailyLinearLayout));


        for(int i=0; i<hourlyLinearLayout.size(); i++){
            paramsHour = hourlyLinearLayout.get(i).getLayoutParams();
            paramsHour.width = (Integer) (Resources.getSystem().getDisplayMetrics().widthPixels/3);
        }

        for(int j=0; j<dailyLinearLayout.size(); j++){
            paramsDay = dailyLinearLayout.get(j).getLayoutParams();
            paramsDay.width = (Integer) (Resources.getSystem().getDisplayMetrics().widthPixels/2);
        }

    }


    public void setCurrentListViewData(){
        currentLocationCallHelper.getCurrentDataFromSQLite();
        double windSpeedMSecond = Double.parseDouble(currentLocationCallHelper.getLocationWindSpeed());
        //String windSpeedKmHour = String.valueOf(windSpeedMSecond*3600/1000);
        DecimalFormat df = new DecimalFormat("#.#");
        String windSpeedKmHour = df.format(windSpeedMSecond*3600/1000);
        valuesList[0] = windSpeedKmHour + " km/h "
                + currentLocationCallHelper.getLocationWindDeg() + "°";
        valuesList[1] = currentLocationCallHelper.getLocationPressure() + " hPa";
        valuesList[2] = "%" + currentLocationCallHelper.getLocationHumidity();
        valuesList[3] = currentLocationCallHelper.getLocationVisibility() + " m";
        customListAdapter.notifyDataSetChanged();
    }

    public void setHourlyScrollViewData(){
        forecastCallingControlHelper.getDetailedForecastDataFromSQLite();
        //System.out.println("FORECAST DATA CALLED");

        hourlyTimeList = new ArrayList<>(48);
        hourlyTempList = new ArrayList<>(48);
        hourlyRainChanceList = new ArrayList<>(48);
        hourlyIconList = new ArrayList<>(48);

        hourlyTimeList.add(findViewById(R.id.firstHourTimeText));
        hourlyTimeList.add(findViewById(R.id.secondHourTimeText));
        hourlyTimeList.add(findViewById(R.id.thirdHourTimeText));
        hourlyTimeList.add(findViewById(R.id.fourthHourTimeText));
        hourlyTimeList.add(findViewById(R.id.fifthHourTimeText));
        hourlyTimeList.add(findViewById(R.id.sixthHourTimeText));
        hourlyTimeList.add(findViewById(R.id.seventhHourTimeText));
        hourlyTimeList.add(findViewById(R.id.eighthHourTimeText));
        hourlyTimeList.add(findViewById(R.id.ninthHourTimeText));
        hourlyTimeList.add(findViewById(R.id.tenthHourTimeText));
        hourlyTimeList.add(findViewById(R.id.eleventhHourTimeText));
        hourlyTimeList.add(findViewById(R.id.twelfthHourTimeText));
        hourlyTimeList.add(findViewById(R.id.thirteenthHourTimeText));
        hourlyTimeList.add(findViewById(R.id.fourteenthHourTimeText));
        hourlyTimeList.add(findViewById(R.id.fifteenthHourTimeText));
        hourlyTimeList.add(findViewById(R.id.sixteenthHourTimeText));
        hourlyTimeList.add(findViewById(R.id.seventeenthHourTimeText));
        hourlyTimeList.add(findViewById(R.id.eighteenthHourTimeText));
        hourlyTimeList.add(findViewById(R.id.nineteenthHourTimeText));
        hourlyTimeList.add(findViewById(R.id.twentiethHourTimeText));
        hourlyTimeList.add(findViewById(R.id.twentyfirstHourTimeText));
        hourlyTimeList.add(findViewById(R.id.twentysecondHourTimeText));
        hourlyTimeList.add(findViewById(R.id.twentythirdHourTimeText));
        hourlyTimeList.add(findViewById(R.id.twentyfourthHourTimeText));

        hourlyTempList.add(findViewById(R.id.firstHourTemp));
        hourlyTempList.add(findViewById(R.id.secondHourTemp));
        hourlyTempList.add(findViewById(R.id.thirdHourTemp));
        hourlyTempList.add(findViewById(R.id.fourthHourTemp));
        hourlyTempList.add(findViewById(R.id.fifthHourTemp));
        hourlyTempList.add(findViewById(R.id.sixthHourTemp));
        hourlyTempList.add(findViewById(R.id.seventhHourTemp));
        hourlyTempList.add(findViewById(R.id.eighthHourTemp));
        hourlyTempList.add(findViewById(R.id.ninthHourTemp));
        hourlyTempList.add(findViewById(R.id.tenthHourTemp));
        hourlyTempList.add(findViewById(R.id.eleventhHourTemp));
        hourlyTempList.add(findViewById(R.id.twelfthHourTemp));
        hourlyTempList.add(findViewById(R.id.thirteenthHourTemp));
        hourlyTempList.add(findViewById(R.id.fourteenthHourTemp));
        hourlyTempList.add(findViewById(R.id.fifteenthHourTemp));
        hourlyTempList.add(findViewById(R.id.sixteenthHourTemp));
        hourlyTempList.add(findViewById(R.id.seventeenthHourTemp));
        hourlyTempList.add(findViewById(R.id.eighteenthHourTemp));
        hourlyTempList.add(findViewById(R.id.nineteenthHourTemp));
        hourlyTempList.add(findViewById(R.id.twentiethHourTemp));
        hourlyTempList.add(findViewById(R.id.twentyfirstHourTemp));
        hourlyTempList.add(findViewById(R.id.twentysecondHourTemp));
        hourlyTempList.add(findViewById(R.id.twentythirdHourTemp));
        hourlyTempList.add(findViewById(R.id.twentyfourthHourTemp));

        hourlyRainChanceList.add(findViewById(R.id.firstHourRainChance));
        hourlyRainChanceList.add(findViewById(R.id.secondHourRainChance));
        hourlyRainChanceList.add(findViewById(R.id.thirdHourRainChance));
        hourlyRainChanceList.add(findViewById(R.id.fourthHourRainChance));
        hourlyRainChanceList.add(findViewById(R.id.fifthHourRainChance));
        hourlyRainChanceList.add(findViewById(R.id.sixthHourRainChance));
        hourlyRainChanceList.add(findViewById(R.id.seventhHourRainChance));
        hourlyRainChanceList.add(findViewById(R.id.eighthHourRainChance));
        hourlyRainChanceList.add(findViewById(R.id.ninthHourRainChance));
        hourlyRainChanceList.add(findViewById(R.id.tenthHourRainChance));
        hourlyRainChanceList.add(findViewById(R.id.eleventhHourRainChance));
        hourlyRainChanceList.add(findViewById(R.id.twelfthHourRainChance));
        hourlyRainChanceList.add(findViewById(R.id.thirteenthHourRainChance));
        hourlyRainChanceList.add(findViewById(R.id.fourteenthHourRainChance));
        hourlyRainChanceList.add(findViewById(R.id.fifteenthHourRainChance));
        hourlyRainChanceList.add(findViewById(R.id.sixteenthHourRainChance));
        hourlyRainChanceList.add(findViewById(R.id.seventeenthHourRainChance));
        hourlyRainChanceList.add(findViewById(R.id.eighteenthHourRainChance));
        hourlyRainChanceList.add(findViewById(R.id.nineteenthHourRainChance));
        hourlyRainChanceList.add(findViewById(R.id.twentiethHourRainChance));
        hourlyRainChanceList.add(findViewById(R.id.twentyfirstHourRainChance));
        hourlyRainChanceList.add(findViewById(R.id.twentysecondHourRainChance));
        hourlyRainChanceList.add(findViewById(R.id.twentythirdHourRainChance));
        hourlyRainChanceList.add(findViewById(R.id.twentyfourthHourRainChance));

        hourlyIconList.add(findViewById(R.id.firstHourImage));
        hourlyIconList.add(findViewById(R.id.secondHourImage));
        hourlyIconList.add(findViewById(R.id.thirdHourImage));
        hourlyIconList.add(findViewById(R.id.fourthHourImage));
        hourlyIconList.add(findViewById(R.id.fifthHourImage));
        hourlyIconList.add(findViewById(R.id.sixthHourImage));
        hourlyIconList.add(findViewById(R.id.seventhHourImage));
        hourlyIconList.add(findViewById(R.id.eighthHourImage));
        hourlyIconList.add(findViewById(R.id.ninthHourImage));
        hourlyIconList.add(findViewById(R.id.tenthHourImage));
        hourlyIconList.add(findViewById(R.id.eleventhHourImage));
        hourlyIconList.add(findViewById(R.id.twelfthHourImage));
        hourlyIconList.add(findViewById(R.id.thirteenthHourImage));
        hourlyIconList.add(findViewById(R.id.fourteenthHourImage));
        hourlyIconList.add(findViewById(R.id.fifteenthHourImage));
        hourlyIconList.add(findViewById(R.id.sixteenthHourImage));
        hourlyIconList.add(findViewById(R.id.seventeenthHourImage));
        hourlyIconList.add(findViewById(R.id.eighteenthHourImage));
        hourlyIconList.add(findViewById(R.id.nineteenthHourImage));
        hourlyIconList.add(findViewById(R.id.twentiethHourImage));
        hourlyIconList.add(findViewById(R.id.twentyfirstHourImage));
        hourlyIconList.add(findViewById(R.id.twentysecondHourImage));
        hourlyIconList.add(findViewById(R.id.twentythirdHourImage));
        hourlyIconList.add(findViewById(R.id.twentyfourthHourImage));

        //System.out.println(hourlyTimeList.size());
        //System.out.println(forecastCallingControlHelper.getHourlyTime().size());
        //System.out.println(forecastCallingControlHelper.getHourlyTemp().size());
        //System.out.println(forecastCallingControlHelper.getHourlyIcon().size());

        for(int i=0; i<hourlyTimeList.size(); i++){
            long hourlyLong = Long.parseLong(forecastCallingControlHelper.getHourlyTime().get(i));
            // convert seconds to milliseconds
            Date hourlyConvertedTime = new java.util.Date(hourlyLong*1000L);
            // the format of your date
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm");
            // give a timezone reference for formatting (see comment at the bottom)
            sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+3"));
            String formattedHourlyTime = sdf.format(hourlyConvertedTime);
            //System.out.println(formattedHourlyTime);
            hourlyTimeList.get(i).setText(formattedHourlyTime);
            hourlyTempList.get(i).setText(forecastCallingControlHelper.getHourlyTemp().get(i)+"°");
            Double hourlyRainChanceDouble = Double.parseDouble(forecastCallingControlHelper.getHourlyPop().get(i));
            int hourlyRainChancePercent = (int) Math.round(hourlyRainChanceDouble*100);
            hourlyRainChanceList.get(i).setText("%"+hourlyRainChancePercent);

            switch (forecastCallingControlHelper.getHourlyIcon().get(i)){
                case "01d":
                    hourlyIconList.get(i).setImageResource(R.drawable.one_day);
                    break;
                case "01n":
                    hourlyIconList.get(i).setImageResource(R.drawable.one_night);
                    break;
                case "02d":
                    hourlyIconList.get(i).setImageResource(R.drawable.two_day);
                    break;
                case "02n":
                    hourlyIconList.get(i).setImageResource(R.drawable.two_night);
                    break;
                case "03d":
                    hourlyIconList.get(i).setImageResource(R.drawable.three_day);
                    break;
                case "03n":
                    hourlyIconList.get(i).setImageResource(R.drawable.three_night);
                    break;
                case "04d":
                    hourlyIconList.get(i).setImageResource(R.drawable.four_day);
                    break;
                case "04n":
                    hourlyIconList.get(i).setImageResource(R.drawable.four_night);
                    break;
                case "09d":
                    hourlyIconList.get(i).setImageResource(R.drawable.nine_day);
                    break;
                case "09n":
                    hourlyIconList.get(i).setImageResource(R.drawable.nine_night);
                    break;
                case "10d":
                    hourlyIconList.get(i).setImageResource(R.drawable.ten_day);
                    break;
                case "10n":
                    hourlyIconList.get(i).setImageResource(R.drawable.ten_night);
                    break;
                case "11d":
                    hourlyIconList.get(i).setImageResource(R.drawable.eleven_day);
                    break;
                case "11n":
                    hourlyIconList.get(i).setImageResource(R.drawable.eleven_night);
                    break;
                case "13d":
                    hourlyIconList.get(i).setImageResource(R.drawable.thirteen_day);
                    break;
                case "13n":
                    hourlyIconList.get(i).setImageResource(R.drawable.thirteen_night);
                    break;
                case "50d":
                    hourlyIconList.get(i).setImageResource(R.drawable.fifty_day);
                    break;
                case "50n":
                    hourlyIconList.get(i).setImageResource(R.drawable.fifty_night);
                    break;
            }
        }



    }

    public void setDailyScrollViewData(){
        forecastCallingControlHelper.getDetailedForecastDataFromSQLite();
        //System.out.println("FORECAST DATA CALLED");

        dailyTimeList = new ArrayList<>(8);
        dailyTempList = new ArrayList<>(8);
        dailyRainChanceList = new ArrayList<>(8);
        dailyIconList = new ArrayList<>(8);

        dailyTimeList.add(findViewById(R.id.firstDailyTimeText));
        dailyTimeList.add(findViewById(R.id.secondDailyTimeText));
        dailyTimeList.add(findViewById(R.id.thirdDailyTimeText));
        dailyTimeList.add(findViewById(R.id.fourthDailyTimeText));
        dailyTimeList.add(findViewById(R.id.fifthDailyTimeText));
        dailyTimeList.add(findViewById(R.id.sixthDailyTimeText));
        dailyTimeList.add(findViewById(R.id.seventhDailyTimeText));
        dailyTimeList.add(findViewById(R.id.eighthDailyTimeText));

        dailyTempList.add(findViewById(R.id.firstDailyTemp));
        dailyTempList.add(findViewById(R.id.secondDailyTemp));
        dailyTempList.add(findViewById(R.id.thirdDailyTemp));
        dailyTempList.add(findViewById(R.id.fourthDailyTemp));
        dailyTempList.add(findViewById(R.id.fifthDailyTemp));
        dailyTempList.add(findViewById(R.id.sixthDailyTemp));
        dailyTempList.add(findViewById(R.id.seventhDailyTemp));
        dailyTempList.add(findViewById(R.id.eighthDailyTemp));

        dailyRainChanceList.add(findViewById(R.id.firstDailyRainChance));
        dailyRainChanceList.add(findViewById(R.id.secondDailyRainChance));
        dailyRainChanceList.add(findViewById(R.id.thirdDailyRainChance));
        dailyRainChanceList.add(findViewById(R.id.fourthDailyRainChance));
        dailyRainChanceList.add(findViewById(R.id.fifthDailyRainChance));
        dailyRainChanceList.add(findViewById(R.id.sixthDailyRainChance));
        dailyRainChanceList.add(findViewById(R.id.seventhDailyRainChance));
        dailyRainChanceList.add(findViewById(R.id.eighthDailyRainChance));

        dailyIconList.add(findViewById(R.id.firstDailyImage));
        dailyIconList.add(findViewById(R.id.secondDailyImage));
        dailyIconList.add(findViewById(R.id.thirdDailyImage));
        dailyIconList.add(findViewById(R.id.fourthDailyImage));
        dailyIconList.add(findViewById(R.id.fifthDailyImage));
        dailyIconList.add(findViewById(R.id.sixthDailyImage));
        dailyIconList.add(findViewById(R.id.seventhDailyImage));
        dailyIconList.add(findViewById(R.id.eighthDailyImage));

        //System.out.println("DAILY-------------------");
        //System.out.println(dailyTimeList.size());
        //System.out.println(forecastCallingControlHelper.getDailyTime());
        //System.out.println(forecastCallingControlHelper.getDailyMinTemp());
        //System.out.println(forecastCallingControlHelper.getDailyMaxTemp());
        //System.out.println(forecastCallingControlHelper.getDailyIcon());

        for(int j=0; j<dailyTimeList.size(); j++){
            long dailyLong = Long.parseLong(forecastCallingControlHelper.getDailyTime().get(j));
            // convert seconds to milliseconds
            Date dailyConvertedTime = new java.util.Date(dailyLong*1000L);
            // the format of your date
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("EEE, dd MMM");
            // give a timezone reference for formatting (see comment at the bottom)
            sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+3"));
            String formattedDailyTime = sdf.format(dailyConvertedTime);
            //System.out.println(formattedDailyTime);
            dailyTimeList.get(j).setText(formattedDailyTime);


            dailyTempList.get(j).setText(forecastCallingControlHelper.getDailyMinTemp().get(j) +
                    "° / " + forecastCallingControlHelper.getDailyMaxTemp().get(j) + "°");
            Double dailyRainChanceDouble = Double.parseDouble(forecastCallingControlHelper.getDailyPop().get(j));
            int dailyRainChancePercent = (int) Math.round(dailyRainChanceDouble*100);
            dailyRainChanceList.get(j).setText("%"+dailyRainChancePercent);
            switch (forecastCallingControlHelper.getDailyIcon().get(j)){
                case "01d":
                    dailyIconList.get(j).setImageResource(R.drawable.one_day);
                    break;
                case "01n":
                    dailyIconList.get(j).setImageResource(R.drawable.one_night);
                    break;
                case "02d":
                    dailyIconList.get(j).setImageResource(R.drawable.two_day);
                    break;
                case "02n":
                    dailyIconList.get(j).setImageResource(R.drawable.two_night);
                    break;
                case "03d":
                    dailyIconList.get(j).setImageResource(R.drawable.three_day);
                    break;
                case "03n":
                    dailyIconList.get(j).setImageResource(R.drawable.three_night);
                    break;
                case "04d":
                    dailyIconList.get(j).setImageResource(R.drawable.four_day);
                    break;
                case "04n":
                    dailyIconList.get(j).setImageResource(R.drawable.four_night);
                    break;
                case "09d":
                    dailyIconList.get(j).setImageResource(R.drawable.nine_day);
                    break;
                case "09n":
                    dailyIconList.get(j).setImageResource(R.drawable.nine_night);
                    break;
                case "10d":
                    dailyIconList.get(j).setImageResource(R.drawable.ten_day);
                    break;
                case "10n":
                    dailyIconList.get(j).setImageResource(R.drawable.ten_night);
                    break;
                case "11d":
                    dailyIconList.get(j).setImageResource(R.drawable.eleven_day);
                    break;
                case "11n":
                    dailyIconList.get(j).setImageResource(R.drawable.eleven_night);
                    break;
                case "13d":
                    dailyIconList.get(j).setImageResource(R.drawable.thirteen_day);
                    break;
                case "13n":
                    dailyIconList.get(j).setImageResource(R.drawable.thirteen_night);
                    break;
                case "50d":
                    dailyIconList.get(j).setImageResource(R.drawable.fifty_day);
                    break;
                case "50n":
                    dailyIconList.get(j).setImageResource(R.drawable.fifty_night);
                    break;
            }

        }
    }

}