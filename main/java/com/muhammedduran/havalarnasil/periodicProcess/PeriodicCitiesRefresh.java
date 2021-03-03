package com.muhammedduran.havalarnasil.periodicProcess;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.muhammedduran.havalarnasil.calls.CitiesCallingControl;

public class PeriodicCitiesRefresh extends Worker {

    CitiesCallingControl citiesCallingControl;
    SharedPreferences sharedPreferences;
    Context periodicContext;

    public PeriodicCitiesRefresh(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.periodicContext = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        refreshCitiesDataPeriodically();
        return Result.success();
    }

    private void refreshCitiesDataPeriodically(){
        //System.out.println("PERIODICALLY CITIES CALLED");
        sharedPreferences = periodicContext.getSharedPreferences("com.muhammedduran.havalarnasil", Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt("CitiesDataUpdateStatus", 0).apply();
        citiesCallingControl = new CitiesCallingControl();
        citiesCallingControl.refreshCitiesData(periodicContext);
    }
}
