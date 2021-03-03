package com.muhammedduran.havalarnasil.calls;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.muhammedduran.havalarnasil.view.MainActivity;

public class CurrentLocationCallHelper {

    Context applicationContext = MainActivity.getContextOfApplication();
    SQLiteDatabase sqLiteDatabase;

    String locationDescription, locationIcon, locationTemp, locationFeelsLike, locationPressure;
    String locationHumidity, locationVisibility, locationWindSpeed, locationWindDeg, locationTime;

    public void getCurrentDataFromSQLite(){

        try {
            sqLiteDatabase = applicationContext.openOrCreateDatabase("Weather", Context.MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM currentLocationCallList", null);
            int locationDescriptionIx = cursor.getColumnIndex("locationDescription");
            int locationIconIx = cursor.getColumnIndex("locationIcon");
            int locationTempIx = cursor.getColumnIndex("locationTemp");
            int locationFeelsLikeIx = cursor.getColumnIndex("locationFeelsLike");
            int locationPressureIx = cursor.getColumnIndex("locationPressure");
            int locationHumidityIx = cursor.getColumnIndex("locationHumidity");
            int locationVisibilityIx = cursor.getColumnIndex("locationVisibility");
            int locationWindSpeedIx = cursor.getColumnIndex("locationWindSpeed");
            int locationWindDegIx = cursor.getColumnIndex("locationWindDeg");
            int locationTimeIx = cursor.getColumnIndex("locationTime");
            while (cursor.moveToNext()){
                locationDescription = cursor.getString(locationDescriptionIx);
                locationIcon = cursor.getString(locationIconIx);
                locationTemp = cursor.getString(locationTempIx);
                locationFeelsLike = cursor.getString(locationFeelsLikeIx);
                locationPressure = cursor.getString(locationPressureIx);
                locationHumidity = cursor.getString(locationHumidityIx);
                locationVisibility = cursor.getString(locationVisibilityIx);
                locationWindSpeed = cursor.getString(locationWindSpeedIx);
                locationWindDeg = cursor.getString(locationWindDegIx);
                locationTime = cursor.getString(locationTimeIx);
            }
            cursor.close();
        }
        catch (Exception e){e.printStackTrace();}
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public String getLocationIcon() {
        return locationIcon;
    }

    public String getLocationTemp() {
        return locationTemp;
    }

    public String getLocationFeelsLike() {
        return locationFeelsLike;
    }

    public String getLocationPressure() {
        return locationPressure;
    }

    public String getLocationHumidity() {
        return locationHumidity;
    }

    public String getLocationVisibility() {
        return locationVisibility;
    }

    public String getLocationWindSpeed() {
        return locationWindSpeed;
    }

    public String getLocationWindDeg() {
        return locationWindDeg;
    }

    public String getLocationTime() {
        return locationTime;
    }
}
