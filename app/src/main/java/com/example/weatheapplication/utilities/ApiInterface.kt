package com.example.weatheapplication.utilities

import com.example.weatheapplication.pojo.ModelClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("weather")
    fun getWeatherData(
        @Query("q") cityName:String,
        @Query("appid") api_key:String

    ):Call<ModelClass>
}