package com.muhammedduran.havalarnasil.calls;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;

import com.muhammedduran.havalarnasil.adapter.MarqueIcAnadoluAdapter;
import com.muhammedduran.havalarnasil.view.MainActivity;

import java.util.ArrayList;

public class CitiesCallingControlHelper {

    Context applicationContext = MainActivity.getContextOfApplication();
    SQLiteDatabase sqLiteDatabase;

    ArrayList<Integer> marmaraTempList;
    ArrayList<String> marmaraIconDescriptionList;
    ArrayList<Integer> icAnadoluTempList;
    ArrayList<String> icAnadoluIconDescriptionList;
    ArrayList<Integer> egeTempList;
    ArrayList<String> egeIconDescriptionList;
    ArrayList<Integer> akdenizTempList;
    ArrayList<String> akdenizIconDescriptionList;
    ArrayList<Integer> karadenizTempList;
    ArrayList<String> karadenizIconDescriptionList;
    ArrayList<Integer> doguAnadoluTempList;
    ArrayList<String> doguAnadoluIconDescriptionList;
    ArrayList<Integer> guneydoguTempList;
    ArrayList<String> guneydoguIconDescriptionList;
    ArrayList<Integer> abroadTempList;
    ArrayList<String> abroadIconDescriptionList;

    public void getCitiesDataFromSQLite(){

        marmaraTempList = new ArrayList<>();
        icAnadoluTempList = new ArrayList<>();
        egeTempList = new ArrayList<>();
        akdenizTempList = new ArrayList<>();
        karadenizTempList = new ArrayList<>();
        doguAnadoluTempList = new ArrayList<>();
        guneydoguTempList = new ArrayList<>();
        marmaraIconDescriptionList = new ArrayList<>();
        icAnadoluIconDescriptionList = new ArrayList<>();
        egeIconDescriptionList = new ArrayList<>();
        akdenizIconDescriptionList = new ArrayList<>();
        karadenizIconDescriptionList = new ArrayList<>();
        doguAnadoluIconDescriptionList = new ArrayList<>();
        guneydoguIconDescriptionList = new ArrayList<>();
        abroadTempList = new ArrayList<>();
        abroadIconDescriptionList = new ArrayList<>();

        try {
            sqLiteDatabase = applicationContext.openOrCreateDatabase("Weather", Context.MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT weathertemplist, weathericonlist FROM weatherList WHERE id >=1  AND id<=11", null);
            int weatherTempIx = cursor.getColumnIndex("weathertemplist");
            int weatherIconIx = cursor.getColumnIndex("weathericonlist");
            //System.out.println("FIRST CURSOR");
            while(cursor.moveToNext()){
                marmaraTempList.add(Integer.parseInt(cursor.getString(weatherTempIx)));
                marmaraIconDescriptionList.add(cursor.getString(weatherIconIx));
            }
            cursor.close();

            cursor = sqLiteDatabase.rawQuery("SELECT weathertemplist, weathericonlist FROM weatherList WHERE id >11  AND id<=24", null);
            weatherTempIx = cursor.getColumnIndex("weathertemplist");
            weatherIconIx = cursor.getColumnIndex("weathericonlist");
            //System.out.println("SECOND CURSOR");
            while(cursor.moveToNext()){
                icAnadoluTempList.add(Integer.parseInt(cursor.getString(weatherTempIx)));
                icAnadoluIconDescriptionList.add(cursor.getString(weatherIconIx));
            }
            cursor.close();

            cursor = sqLiteDatabase.rawQuery("SELECT weathertemplist, weathericonlist FROM weatherList WHERE id >24  AND id<=32", null);
            weatherTempIx = cursor.getColumnIndex("weathertemplist");
            weatherIconIx = cursor.getColumnIndex("weathericonlist");
            //System.out.println("THIRD CURSOR");
            while(cursor.moveToNext()){
                egeTempList.add(Integer.parseInt(cursor.getString(weatherTempIx)));
                egeIconDescriptionList.add(cursor.getString(weatherIconIx));
            }
            cursor.close();

            cursor = sqLiteDatabase.rawQuery("SELECT weathertemplist, weathericonlist FROM weatherList WHERE id >32  AND id<=40", null);
            weatherTempIx = cursor.getColumnIndex("weathertemplist");
            weatherIconIx = cursor.getColumnIndex("weathericonlist");
            //System.out.println("FOURTH CURSOR");
            while(cursor.moveToNext()){
                akdenizTempList.add(Integer.parseInt(cursor.getString(weatherTempIx)));
                akdenizIconDescriptionList.add(cursor.getString(weatherIconIx));
            }
            cursor.close();

            cursor = sqLiteDatabase.rawQuery("SELECT weathertemplist, weathericonlist FROM weatherList WHERE id >40  AND id<=58", null);
            weatherTempIx = cursor.getColumnIndex("weathertemplist");
            weatherIconIx = cursor.getColumnIndex("weathericonlist");
            //System.out.println("FIFTH CURSOR");
            while(cursor.moveToNext()){
                karadenizTempList.add(Integer.parseInt(cursor.getString(weatherTempIx)));
                karadenizIconDescriptionList.add(cursor.getString(weatherIconIx));
            }
            cursor.close();

            cursor = sqLiteDatabase.rawQuery("SELECT weathertemplist, weathericonlist FROM weatherList WHERE id >58  AND id<=72", null);
            weatherTempIx = cursor.getColumnIndex("weathertemplist");
            weatherIconIx = cursor.getColumnIndex("weathericonlist");
            //System.out.println("SIXTH CURSOR");
            while(cursor.moveToNext()){
                doguAnadoluTempList.add(Integer.parseInt(cursor.getString(weatherTempIx)));
                doguAnadoluIconDescriptionList.add(cursor.getString(weatherIconIx));
            }
            cursor.close();

            cursor = sqLiteDatabase.rawQuery("SELECT weathertemplist, weathericonlist FROM weatherList WHERE id >72  AND id<=81", null);
            weatherTempIx = cursor.getColumnIndex("weathertemplist");
            weatherIconIx = cursor.getColumnIndex("weathericonlist");
            //System.out.println("SEVENTH CURSOR");
            while(cursor.moveToNext()){
                guneydoguTempList.add(Integer.parseInt(cursor.getString(weatherTempIx)));
                guneydoguIconDescriptionList.add(cursor.getString(weatherIconIx));
            }
            cursor.close();

            cursor = sqLiteDatabase.rawQuery("SELECT weathertemplist, weathericonlist FROM weatherList WHERE id >81  AND id<=97", null);
            weatherTempIx = cursor.getColumnIndex("weathertemplist");
            weatherIconIx = cursor.getColumnIndex("weathericonlist");
            //System.out.println("SEVENTH CURSOR");
            while(cursor.moveToNext()){
                abroadTempList.add(Integer.parseInt(cursor.getString(weatherTempIx)));
                abroadIconDescriptionList.add(cursor.getString(weatherIconIx));
            }
            cursor.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public ArrayList<Integer> getMarmaraTempList() {
        return marmaraTempList;
    }

    public ArrayList<String> getMarmaraIconDescriptionList() {
        return marmaraIconDescriptionList;
    }

    public ArrayList<Integer> getIcAnadoluTempList() {
        return icAnadoluTempList;
    }

    public ArrayList<String> getIcAnadoluIconDescriptionList() {
        return icAnadoluIconDescriptionList;
    }

    public ArrayList<Integer> getEgeTempList() {
        return egeTempList;
    }

    public ArrayList<String> getEgeIconDescriptionList() {
        return egeIconDescriptionList;
    }

    public ArrayList<Integer> getAkdenizTempList() {
        return akdenizTempList;
    }

    public ArrayList<String> getAkdenizIconDescriptionList() {
        return akdenizIconDescriptionList;
    }

    public ArrayList<Integer> getKaradenizTempList() {
        return karadenizTempList;
    }

    public ArrayList<String> getKaradenizIconDescriptionList() {
        return karadenizIconDescriptionList;
    }

    public ArrayList<Integer> getDoguAnadoluTempList() {
        return doguAnadoluTempList;
    }

    public ArrayList<String> getDoguAnadoluIconDescriptionList() {
        return doguAnadoluIconDescriptionList;
    }

    public ArrayList<Integer> getGuneydoguTempList() {
        return guneydoguTempList;
    }

    public ArrayList<String> getGuneydoguIconDescriptionList() {
        return guneydoguIconDescriptionList;
    }

    public ArrayList<Integer> getAbroadTempList() {
        return abroadTempList;
    }

    public ArrayList<String> getAbroadIconDescriptionList() {
        return abroadIconDescriptionList;
    }
}
