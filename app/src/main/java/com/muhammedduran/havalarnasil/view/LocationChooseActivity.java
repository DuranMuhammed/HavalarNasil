package com.muhammedduran.havalarnasil.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.muhammedduran.havalarnasil.calls.CitiesCallingControl;
import com.muhammedduran.havalarnasil.calls.CurrentLocationCall;
import com.muhammedduran.havalarnasil.calls.ForecastCallingControl;
import com.muhammedduran.havalarnasil.R;
import com.muhammedduran.havalarnasil.Retrofit.ApiClient;
import com.muhammedduran.havalarnasil.Retrofit.WeatherAPILocation;
import com.muhammedduran.havalarnasil.Retrofit.WeatherAPILocationDetails;
import com.muhammedduran.havalarnasil.model.location.WeatherModelLocation;
import com.muhammedduran.havalarnasil.model.location.WeatherModelLocationDetails;

import retrofit2.Call;

public class LocationChooseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static Context contextOfApplication;
    Call<WeatherModelLocation> call;
    Call<WeatherModelLocationDetails> detailsCall;
    Spinner spinnerCities;
    Spinner spinnerDistricts;
    Button nextButton;
    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<CharSequence> districtsAdapter;
    CitiesCallingControl citiesCallingControl;
    ForecastCallingControl forecastCallingControl;
    CurrentLocationCall currentLocationCall;
    int selectedCityIdPosition;
    int selectedDistrictIdPosition;
    String selectedDistrictId;
    String selectedCity;
    String selectedDistrict;
    String[] cityIdArray;
    String latitude;
    String longitude;
    WeatherAPILocation weatherAPILocation = ApiClient.getClient().create(WeatherAPILocation.class);
    WeatherAPILocationDetails weatherAPILocationDetails = ApiClient.getClient().create(WeatherAPILocationDetails.class);
    Intent intent;
    SharedPreferences sharedPreferences;

    public static Context getContextOfApplication() {
        return contextOfApplication;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_choose);
        contextOfApplication = getApplicationContext();

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);

        sharedPreferences = this.getSharedPreferences("com.muhammedduran.havalarnasil", MODE_PRIVATE);
        if(sharedPreferences.getInt("Locationselected", 0) != 0){
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        spinnerCities = (Spinner) findViewById(R.id.cities_spinner);
        spinnerDistricts = (Spinner) findViewById(R.id.districts_spinner);
        nextButton = findViewById(R.id.button_next);
        citiesCallingControl = new CitiesCallingControl();
        forecastCallingControl = new ForecastCallingControl();
        currentLocationCall = new CurrentLocationCall();

        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = ArrayAdapter.createFromResource(this, R.array.cities_array, R.layout.custom_spinner_item);
        createDropDowns();
    }

    public void createDropDowns(){
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerCities.setAdapter(adapter);
        spinnerCities.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // An item was selected. You can retrieve the selected item using
        //System.out.println(parent.getItemAtPosition(position));
        selectedCity = (String) parent.getSelectedItem();
        selectedCityIdPosition = parent.getSelectedItemPosition();
        //System.out.println(selectedCityIdPosition);
        if(selectedCityIdPosition != 0){
            spinnerDistricts.setVisibility(View.VISIBLE);

            switch (selectedCityIdPosition){
                case 1:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Adana_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Adana_districts_id_array);
                    //cityIdArray = getResources().getStringArray(R.array.Adana_districts_id_array);
                    break;
                case 2:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Adiyaman_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Adiyaman_districts_id_array);
                    break;
                case 3:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Afyon_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Afyon_districts__id_array);
                    break;
                case 4:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Agri_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Agri_districts_id_array);
                    break;
                case 5:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Aksaray_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Aksaray_districts_id_array);
                    break;
                case 6:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Amasya_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Amasya_districts_id_array);
                    break;
                case 7:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Ankara_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Ankara_districts_id_array);
                    break;
                case 8:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Antalya_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Antalya_districts_id_array);
                    break;
                case 9:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Ardahan_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Ardahan_districts_id_array);
                    break;
                case 10:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Artvin_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Artvin_districts_id_array);
                    break;
                case 11:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Aydin_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Aydin_districts_id_array);
                    break;
                case 12:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Balikesir_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Balikesir_districts_id_array);
                    break;
                case 13:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Bartin_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Bartin_districts_id_array);
                    break;
                case 14:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Batman_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Batman_districts_id_array);
                    break;
                case 15:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Bayburt_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Bayburt_districts_id_array);
                    break;
                case 16:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Bilecik_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Bilecik_districts_id_array);
                    break;
                case 17:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Bingol_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Bingol_districts_id_array);
                    break;
                case 18:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Bitlis_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Bitlis_districts_id_array);
                    break;
                case 19:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Bolu_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Bolu_districts_id_array);
                    break;
                case 20:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Burdur_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Burdur_districts_id_array);
                    break;
                case 21:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Bursa_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Bursa_districts_id_array);
                    break;
                case 22:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Canakkale_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Canakkale_districts_id_array);
                    break;
                case 23:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Cankiri_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Cankiri_districts_id_array);
                    break;
                case 24:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Corum_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Corum_districts_id_array);
                    break;
                case 25:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Denizli_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Denizli_districts_id_array);
                    break;
                case 26:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Diyarbakir_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Diyarbakir_districts_id_array);
                    break;
                case 27:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Duzce_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Duzce_districts_id_array);
                    break;
                case 28:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Edirne_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Edirne_districts_id_array);
                    break;
                case 29:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Elazig_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Elazig_districts_id_array);
                    break;
                case 30:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Erzincan_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Erzincan_districts_id_array);
                    break;
                case 31:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Erzurum_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Erzurum_districts_id_array);
                    break;
                case 32:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Eskisehir_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Eskisehir_districts_id_array);
                    break;
                case 33:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Gaziantep_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Gaziantep_districts_id_array);
                    break;
                case 34:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Giresun_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Giresun_districts_id_array);
                    break;
                case 35:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Gumushane_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Gumushane_districts_id_array);
                    break;
                case 36:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Hakkari_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Hakkari_districts_id_array);
                    break;
                case 37:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Hatay_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Hatay_districts_id_array);
                    break;
                case 38:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Igdir_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Igdir_districts_id_array);
                    break;
                case 39:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Isparta_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Isparta_districts_id_array);
                    break;
                case 40:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Istanbul_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Istanbul_districts_id_array);
                    break;
                case 41:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Izmir_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Izmir_districts_id_array);
                    break;
                case 42:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Kahramanmaras_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Kahramanmaras_districts_id_array);
                    break;
                case 43:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Karabuk_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Karabuk_districts_id_array);
                    break;
                case 44:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Karaman_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Karaman_districts_id_array);
                    break;
                case 45:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Kars_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Kars_districts_id_array);
                    break;
                case 46:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Kastamonu_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Kastamonu_districts_id_array);
                    break;
                case 47:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Kayseri_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Kayseri_districts_id_array);
                    break;
                case 48:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Kirikkale_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Kirikkale_districts_id_array);
                    break;
                case 49:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Kirklareli_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Kirklareli_districts_id_array);
                    break;
                case 50:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Kirsehir_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Kirsehir_districts_id_array);
                    break;
                case 51:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Kilis_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Kilis_districts_id_array);
                    break;
                case 52:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Kocaeli_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Kocaeli_districts_id_array);
                    break;
                case 53:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Konya_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Konya_districts_id_array);
                    break;
                case 54:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Kutahya_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Kutahya_districts_id_array);
                    break;
                case 55:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Malatya_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Malatya_districts_id_array);
                    break;
                case 56:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Manisa_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Manisa_districts_id_array);
                    break;
                case 57:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Mardin_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Mardin_districts_id_array);
                    break;
                case 58:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Mersin_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Mersin_districts_id_array);
                    break;
                case 59:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Mugla_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Mugla_districts_id_array);
                    break;
                case 60:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Mus_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Mus_districts_id_array);
                    break;
                case 61:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Nevsehir_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Nevsehir_districts_id_array);
                    break;
                case 62:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Nigde_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Nigde_districts_id_array);
                    break;
                case 63:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Ordu_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Ordu_districts_id_array);
                    break;
                case 64:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Osmaniye_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Osmaniye_districts_id_array);
                    break;
                case 65:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Rize_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Rize_districts_id_array);
                    break;
                case 66:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Sakarya_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Sakarya_districts_id_array);
                    break;
                case 67:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Samsun_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Samsun_districts_id_array);
                    break;
                case 68:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Siirt_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Siirt_districts_id_array);
                    break;
                case 69:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Sinop_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Sinop_districts_id_array);
                    break;
                case 70:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Sivas_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Sivas_districts_id_array);
                    break;
                case 71:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Sanliurfa_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Sanliurfa_districts_id_array);
                    break;
                case 72:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Sirnak_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Sirnak_districts_id_array);
                    break;
                case 73:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Tekirdag_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Tekirdag_districts_id_array);
                    break;
                case 74:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Tokat_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Tokat_districts_id_array);
                    break;
                case 75:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Trabzon_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Trabzon_districts_id_array);
                    break;
                case 76:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Tunceli_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Tunceli_districts_id_array);
                    break;
                case 77:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Usak_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Usak_districts_id_array);
                    break;
                case 78:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Van_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Van_districts_id_array);
                    break;
                case 79:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Yalova_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Yalova_districts_id_array);
                    break;
                case 80:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Yozgat_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Yozgat_districts_id_array);
                    break;
                case 81:
                    districtsAdapter = ArrayAdapter.createFromResource(this, R.array.Zonguldak_district_array, R.layout.custom_spinner_item);
                    spinnerDistricts.setAdapter(districtsAdapter);
                    cityIdArray =  getApplication().getResources().getStringArray(R.array.Zonguldak_districts_id_array);
                    break;

            }
            spinnerDistricts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //System.out.println(parent.getItemAtPosition(position));
                    selectedDistrict = (String) parent.getSelectedItem();
                    selectedDistrictIdPosition = parent.getSelectedItemPosition();
                    //System.out.println(selectedDistrictIdPosition);
                    if(selectedDistrictIdPosition != 0){
                        nextButton.setVisibility(View.VISIBLE);

                        //System.out.println(cityIdArray[position-1]);
                        selectedDistrictId = cityIdArray[position-1];
                        //////////////////////////

                    }
                    else{
                        nextButton.setVisibility(View.INVISIBLE);
                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else{
            spinnerDistricts.setVisibility(View.INVISIBLE);
            citiesCallingControl.createLists();
            citiesCallingControl.callAllCities();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void next(View view){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getActiveNetworkInfo() != null) {
            if (connectivityManager.getActiveNetworkInfo().isConnected()) {
                sharedPreferences.edit().putInt("Locationselected", 1).apply();
                //System.out.println("SELECTED CİTY: "+selectedCity);
                //System.out.println("SELECTED DISTRICT: "+selectedDistrict);
                sharedPreferences.edit().putString("selectedCity",selectedCity).apply();
                sharedPreferences.edit().putString("selectedDistrict", selectedDistrict).apply();
                sharedPreferences.edit().putString("selectedDistrictId", selectedDistrictId).apply();
                sharedPreferences.edit().putString("currentControlString", "FromLocation").apply();
                sharedPreferences.edit().putString("forecastControlString", "FromLocation").apply();
                sharedPreferences.edit().putString("CitiesDataControlString", "FromLocation").apply();
                //forecastCallingControl.callForecast(selectedDistrictId);
                currentLocationCall.callCurrentLocationData(selectedDistrictId);
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Lütfen internet bağlantınızı kontrol edin!",
                    Toast.LENGTH_LONG).show();
        }

    }
}