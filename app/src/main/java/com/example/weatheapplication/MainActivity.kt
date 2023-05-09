package com.example.weatheapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.weatheapplication.databinding.ActivityMainBinding
import com.example.weatheapplication.pojo.ModelClass
import com.example.weatheapplication.utilities.ApiUtilities
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

/*import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import org.json.JSONObject
import java.net.URL*/

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding=DataBindingUtil.setContentView(this, R.layout.activity_main)
        supportActionBar?.hide()

    }

    private fun getData(cityName: String){

        activityMainBinding.pbLoading.visibility= View.VISIBLE
        ApiUtilities.getApiInterface()?.getWeatherData(cityName,"a81ffb312451c083f35db952501585b3")?.enqueue(object :
            Callback<ModelClass> {
            override fun onResponse(call: Call<ModelClass>, response: Response<ModelClass>) {
                if(response.isSuccessful){
                    setData(response.body())
                }
            }

            override fun onFailure(call: Call<ModelClass>, t: Throwable) {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setData(body: ModelClass?){
        val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm")
        val currentDate = sdf.format(Date())
        activityMainBinding.tvDateAndTime.text=body!!.name + " " + currentDate
        activityMainBinding.tvDayMaxTemp.text = "High "+kelvinToFar(body!!.main.temp_max).roundToInt()+" F"
        activityMainBinding.tvDayMinTemp.text = "High "+kelvinToFar(body!!.main.temp_min).roundToInt()+" F"
        activityMainBinding.tvTemp.text = "High "+kelvinToFar(body!!.main.temp).roundToInt()+" F"
        activityMainBinding.tvFeelsLike.text = "High "+kelvinToFar(body!!.main.feels_like).roundToInt()+" F"
        activityMainBinding.tvHumidity.text = body.main.humidity.toString() +" %"
        activityMainBinding.tvWindspeed.text = body.wind.speed.toString() + " mps"
        activityMainBinding.tvWeatherType.text = body.weather[0].main



    }

    private fun kelvinToFar(temp: Double):Double
    {
        var cp_temp = temp
        cp_temp = cp_temp.minus(273)
        cp_temp = cp_temp.times(1.8)
        cp_temp = cp_temp.plus(32)

        return cp_temp.toBigDecimal().setScale(1,RoundingMode.UP).toDouble()
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