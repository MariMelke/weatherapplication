package com.example.weatheapplication.pojo

import com.google.gson.annotations.SerializedName;

data class ModelClass (

        @SerializedName("weather") val weather:List<WeatherClass>,
        @SerializedName("main")val main:Main,
        @SerializedName("wind")val wind:Wind,
        @SerializedName("name")val name:String

        )