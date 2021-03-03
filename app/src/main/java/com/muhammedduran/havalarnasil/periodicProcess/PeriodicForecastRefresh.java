package com.muhammedduran.havalarnasil.periodicProcess;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.muhammedduran.havalarnasil.calls.ForecastCallingControl;

public class PeriodicForecastRefresh extends Worker {

    ForecastCallingControl forecastCallingControl;
    String selectedDistrictId;
    SharedPreferences sharedPreferences;
    Context periodicContext;

    public PeriodicForecastRefresh(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.periodicContext = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        refreshForecastDataPeriodically();
        return Result.success();
    }


    private void refreshForecastDataPeriodically(){
        //System.out.println("PERIODICALLY FORECAST CALLED");
        sharedPreferences = periodicContext.getSharedPreferences("com.muhammedduran.havalarnasil", Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt("forecastDataUpdateStatus", 0).apply();
        selectedDistrictId = sharedPreferences.getString("selectedDistrictId", "NULL");
        forecastCallingControl = new ForecastCallingControl();
        forecastCallingControl.refreshForecast(selectedDistrictId, periodicContext);
    }
}
