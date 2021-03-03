package com.muhammedduran.havalarnasil.calls;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.muhammedduran.havalarnasil.view.MainActivity;

import java.util.ArrayList;

public class ForecastCallingControlHelper {

    Context applicationContext = MainActivity.getContextOfApplication();
    SQLiteDatabase sqLiteDatabase;

    String currentTime, sunrise, sunset, currentTemp, currentfeelslike;
    String currentpressure, currenthumidity, currentdewpoint, currentuvi, currentclouds;
    String currentvisibility, currentwindSpeed, currentwindDeg, currentDescription, currentIcon;

    ArrayList<String> hourlyTime, hourlyTemp, hourlyFeelslike, hourlyPressure, hourlyHumidity;
    ArrayList<String> hourlyWindSpeed, hourlyWindDeg, hourlyPop, hourlyDescription, hourlyIcon;

    ArrayList<String> dailyTime, dailySunrise, dailySunset, dailyMinTemp, dailyMaxTemp;
    ArrayList<String> dailyPressure, dailyHumidity, dailyWindSpeed, dailyWindDeg;
    ArrayList<String> dailyPop, dailyDescription, dailyIcon;


    public void getDetailedForecastDataFromSQLite(){


        hourlyTime = new ArrayList<>();
        hourlyTemp = new ArrayList<>();
        hourlyFeelslike = new ArrayList<>();
        hourlyPressure = new ArrayList<>();
        hourlyHumidity = new ArrayList<>();
        hourlyWindSpeed = new ArrayList<>();
        hourlyWindDeg = new ArrayList<>();
        hourlyPop = new ArrayList<>();
        hourlyDescription = new ArrayList<>();
        hourlyIcon = new ArrayList<>();
        dailyTime = new ArrayList<>();
        dailySunrise = new ArrayList<>();
        dailySunset = new ArrayList<>();
        dailyMinTemp = new ArrayList<>();
        dailyMaxTemp = new ArrayList<>();
        dailyPressure = new ArrayList<>();
        dailyHumidity = new ArrayList<>();
        dailyWindSpeed = new ArrayList<>();
        dailyWindDeg = new ArrayList<>();
        dailyPop = new ArrayList<>();
        dailyDescription = new ArrayList<>();
        dailyIcon = new ArrayList<>();

        try {
            sqLiteDatabase = applicationContext.openOrCreateDatabase("Weather", Context.MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM currentForecastList", null);
            int currentTimeIx = cursor.getColumnIndex("currentTime");
            int sunriseIx =  cursor.getColumnIndex("sunrise");
            int sunsetIx =  cursor.getColumnIndex("sunset");
            int currentTempIx = cursor.getColumnIndex("currentTemp");
            int currentFeelsLikeIx = cursor.getColumnIndex("feelslike");
            int currentPressureIx =  cursor.getColumnIndex("pressure");
            int currentHumidityIx = cursor.getColumnIndex("humidity");
            int currentDewPointIx = cursor.getColumnIndex("dewpoint");
            int currentUviIx = cursor.getColumnIndex("uvi");
            int currentCloudsIx = cursor.getColumnIndex("clouds");
            int currentVisibilityIx = cursor.getColumnIndex("visibility");
            int currentWindSpeedIx = cursor.getColumnIndex("windSpeed");
            int currentWindDegIx = cursor.getColumnIndex("windDeg");
            int currentDescriptionIx = cursor.getColumnIndex("currentDescription");
            int currentIconIx = cursor.getColumnIndex("currentIcon");
            while (cursor.moveToNext()){
                currentTime = cursor.getString(currentTimeIx);
                sunrise = cursor.getString(sunriseIx);
                sunset = cursor.getString(sunsetIx);
                currentTemp = cursor.getString(currentTempIx);
                currentfeelslike = cursor.getString(currentFeelsLikeIx);
                currentpressure = cursor.getString(currentPressureIx);
                currenthumidity = cursor.getString(currentHumidityIx);
                currentdewpoint = cursor.getString(currentDewPointIx);
                currentuvi = cursor.getString(currentUviIx);
                currentclouds = cursor.getString(currentCloudsIx);
                currentvisibility = cursor.getString(currentVisibilityIx);
                currentwindSpeed = cursor.getString(currentWindSpeedIx);
                currentwindDeg = cursor.getString(currentWindDegIx);
                currentDescription = cursor.getString(currentDescriptionIx);
                currentIcon = cursor.getString(currentIconIx);
            }
            cursor.close();

            cursor = sqLiteDatabase.rawQuery("SELECT * FROM hourlyForecastList", null);
            int hourlyTimeIx = cursor.getColumnIndex("hourlyTime");
            int hourlyTempIx = cursor.getColumnIndex("hourlyTemp");
            int hourlyFeelslikeIx = cursor.getColumnIndex("hourlyFeelslike");
            int hourlyPressureIx = cursor.getColumnIndex("hourlyPressure");
            int hourlyHumidityIx = cursor.getColumnIndex("hourlyHumidity");
            int hourlyWindSpeedIx = cursor.getColumnIndex("hourlyWindSpeed");
            int hourlyWindDegIx = cursor.getColumnIndex("hourlyWindDeg");
            int hourlyPopIx = cursor.getColumnIndex("hourlyPop");
            int hourlyDescriptionIx = cursor.getColumnIndex("hourlyDescription");
            int hourlyIconIx = cursor.getColumnIndex("hourlyIcon");
            while (cursor.moveToNext()){
                hourlyTime.add(cursor.getString(hourlyTimeIx));
                hourlyTemp.add(cursor.getString(hourlyTempIx));
                hourlyFeelslike.add(cursor.getString(hourlyFeelslikeIx));
                hourlyPressure.add(cursor.getString(hourlyPressureIx));
                hourlyHumidity.add(cursor.getString(hourlyHumidityIx));
                hourlyWindSpeed.add(cursor.getString(hourlyWindSpeedIx));
                hourlyWindDeg.add(cursor.getString(hourlyWindDegIx));
                hourlyPop.add(cursor.getString(hourlyPopIx));
                hourlyDescription.add(cursor.getString(hourlyDescriptionIx));
                hourlyIcon.add(cursor.getString(hourlyIconIx));
            }
            cursor.close();

            cursor = sqLiteDatabase.rawQuery("SELECT * FROM dailyForecastList", null);
            int dailyTimeIx = cursor.getColumnIndex("dailyTime");
            int dailySunriseIx = cursor.getColumnIndex("dailySunrise");
            int dailySunsetIx = cursor.getColumnIndex("dailySunset");
            int dailyMinTempIx = cursor.getColumnIndex("dailyMinTemp");
            int dailyMaxTempIx = cursor.getColumnIndex("dailyMaxTemp");
            int dailyPressureIx = cursor.getColumnIndex("dailyPressure");
            int dailyHumidityIx = cursor.getColumnIndex("dailyHumidity");
            int dailyWindSpeedIx = cursor.getColumnIndex("dailyWindSpeed");
            int dailyWindDegIx = cursor.getColumnIndex("dailyWindDeg");
            int dailyPopIx = cursor.getColumnIndex("dailyPop");
            int dailyDescriptionIx = cursor.getColumnIndex("dailyDescription");
            int dailyIconIx = cursor.getColumnIndex("dailyIcon");
            while(cursor.moveToNext()){
                dailyTime.add(cursor.getString(dailyTimeIx));
                dailySunrise.add(cursor.getString(dailySunriseIx));
                dailySunset.add(cursor.getString(dailySunsetIx));
                dailyMinTemp.add(cursor.getString(dailyMinTempIx));
                dailyMaxTemp.add(cursor.getString(dailyMaxTempIx));
                dailyPressure.add(cursor.getString(dailyPressureIx));
                dailyHumidity.add(cursor.getString(dailyHumidityIx));
                dailyWindSpeed.add(cursor.getString(dailyWindSpeedIx));
                dailyWindDeg.add(cursor.getString(dailyWindDegIx));
                dailyPop.add(cursor.getString(dailyPopIx));
                dailyDescription.add(cursor.getString(dailyDescriptionIx));
                dailyIcon.add(cursor.getString(dailyIconIx));
            }
            cursor.close();

        }catch (Exception e){e.printStackTrace();}
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getCurrentTemp() {
        return currentTemp;
    }

    public String getCurrentfeelslike() {
        return currentfeelslike;
    }

    public String getCurrentpressure() {
        return currentpressure;
    }

    public String getCurrenthumidity() {
        return currenthumidity;
    }

    public String getCurrentdewpoint() {
        return currentdewpoint;
    }

    public String getCurrentuvi() {
        return currentuvi;
    }

    public String getCurrentclouds() {
        return currentclouds;
    }

    public String getCurrentvisibility() {
        return currentvisibility;
    }

    public String getCurrentwindSpeed() {
        return currentwindSpeed;
    }

    public String getCurrentwindDeg() {
        return currentwindDeg;
    }

    public String getCurrentDescription() {
        return currentDescription;
    }

    public String getCurrentIcon() {
        return currentIcon;
    }

    public ArrayList<String> getHourlyTime() {
        return hourlyTime;
    }

    public ArrayList<String> getHourlyTemp() {
        return hourlyTemp;
    }

    public ArrayList<String> getHourlyFeelslike() {
        return hourlyFeelslike;
    }

    public ArrayList<String> getHourlyPressure() {
        return hourlyPressure;
    }

    public ArrayList<String> getHourlyHumidity() {
        return hourlyHumidity;
    }

    public ArrayList<String> getHourlyWindSpeed() {
        return hourlyWindSpeed;
    }

    public ArrayList<String> getHourlyWindDeg() {
        return hourlyWindDeg;
    }

    public ArrayList<String> getHourlyPop() {
        return hourlyPop;
    }

    public ArrayList<String> getHourlyDescription() {
        return hourlyDescription;
    }

    public ArrayList<String> getHourlyIcon() {
        return hourlyIcon;
    }

    public ArrayList<String> getDailyTime() {
        return dailyTime;
    }

    public ArrayList<String> getDailySunrise() {
        return dailySunrise;
    }

    public ArrayList<String> getDailySunset() {
        return dailySunset;
    }

    public ArrayList<String> getDailyMinTemp() {
        return dailyMinTemp;
    }

    public ArrayList<String> getDailyMaxTemp() {
        return dailyMaxTemp;
    }

    public ArrayList<String> getDailyPressure() {
        return dailyPressure;
    }

    public ArrayList<String> getDailyHumidity() {
        return dailyHumidity;
    }

    public ArrayList<String> getDailyWindSpeed() {
        return dailyWindSpeed;
    }

    public ArrayList<String> getDailyWindDeg() {
        return dailyWindDeg;
    }

    public ArrayList<String> getDailyPop() {
        return dailyPop;
    }

    public ArrayList<String> getDailyDescription() {
        return dailyDescription;
    }

    public ArrayList<String> getDailyIcon() {
        return dailyIcon;
    }
}
