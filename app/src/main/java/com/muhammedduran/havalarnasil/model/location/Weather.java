package com.muhammedduran.havalarnasil.model.location;

import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("description")
    public String descriptionSimple;

    @SerializedName("icon")
    public String iconSimple;
}
