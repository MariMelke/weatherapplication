package com.example.weatheapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
/*import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import org.json.JSONObject
import java.net.URL*/

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /*private fun runRequest()
    {
        val cityName = "Mclean"
        val url = "https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=a81ffb312451c083f35db952501585b3"
        val resultjson = URL(url).readText()
        Log.d("Weather Report",resultjson)
        val jsonObj = JSONObject(resultjson)
        val weather_ = jsonObj.getJSONObject("weather")
        val main = jsonObj.getJSONObject("main")
        val wind = jsonObj.getJSONObject("wind")
        val curr_temp = main.getString("temp")
        val feels_like = main.getString("feels_like")
        val min_temp = main.getString("temp_min")
        val max_temp = main.getString("temp_max")
        val humidity = main.getString("humidity")
        val weather_type = weather_.getString("main")
        val wind_speed = wind.getString("speed")

        val maxTempText = findViewById<TextView>(R.id.tv_day_max_temp) as TextView
        val minTempText = findViewById<TextView>(R.id.tv_day_min_temp) as TextView
        val cityText = findViewById<TextView>(R.id.tv_date_and_time) as TextView
        val currTempText = findViewById<TextView>(R.id.tv_temp) as TextView
        val iconText = findViewById<TextView>(R.id.iv_weather_icon) as ImageView
        val feelsLikeText = findViewById<TextView>(R.id.tv_feels_like) as TextView
        val currDesText = findViewById<TextView>(R.id.tv_weather_type) as TextView
        val windText = findViewById<TextView>(R.id.tv_windspeed) as TextView
        val humidityText = findViewById<TextView>(R.id.tv_humidity) as TextView

        maxTempText.text = max_temp
        minTempText.text = min_temp
        cityText.text = cityName
        currTempText.text = curr_temp
        feelsLikeText.text = feels_like
        currDesText.text = weather_type
        windText.text = wind_speed
        humidityText.text = humidity

    }*/

}