package com.example.weatheapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
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
import kotlinx.coroutines.*

/*import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import org.json.JSONObject
import java.net.URL*/

class MainActivity : AppCompatActivity() {

    private val scope = MainScope()

    private lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        activityMainBinding=DataBindingUtil.setContentView(this, R.layout.activity_main)
        supportActionBar?.hide()
        activityMainBinding.rlMainLayout.visibility=View.VISIBLE

        activityMainBinding.etGetCityName.setOnEditorActionListener({v,actionId, keyEvent ->
            if(actionId==EditorInfo.IME_ACTION_SEARCH){
                val cityName: EditText= findViewById(R.id.et_get_city_name)
                getData(cityName.text.toString())

                val view=this.currentFocus
                if(view!=null)
                {
                    val imm:InputMethodManager=getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken,0)
                    activityMainBinding.etGetCityName.clearFocus()
                }
                true
            }else false
        })

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
        activityMainBinding.tvDayMinTemp.text = "Low "+kelvinToFar(body!!.main.temp_min).roundToInt()+" F"
        activityMainBinding.tvTemp.text = ""+kelvinToFar(body!!.main.temp).roundToInt()+" F"
        activityMainBinding.tvFeelsLike.text = "Feels Like "+kelvinToFar(body!!.main.feels_like).roundToInt()+" F"
        activityMainBinding.tvHumidity.text = body.main.humidity.toString() +" %"
        activityMainBinding.tvWindspeed.text = body.wind.speed.toString() + " mps"
        activityMainBinding.tvWeatherType.text = body.weather[0].main

        updateUI(body.weather[0].id)

    }

    private fun updateUI(id: Int) {
        if(id in 200..232){
            activityMainBinding.ivWeatherIcon.setImageResource(R.drawable.thunderstorm)
        }
        else if(id in 300..321 || id in 511..531){
            activityMainBinding.ivWeatherIcon.setImageResource(R.drawable.drizzle)
        }
        else if(id in 500..504){
            activityMainBinding.ivWeatherIcon.setImageResource(R.drawable.rain)
        }
        else if(id in 600..622){
            activityMainBinding.ivWeatherIcon.setImageResource(R.drawable.snow)
        }
        else if(id in 701..781){
            activityMainBinding.ivWeatherIcon.setImageResource(R.drawable.mist)
        }
        else if(id==800){
            activityMainBinding.ivWeatherIcon.setImageResource(R.drawable.sunny)
        }
        else if(id in 801..804){
            activityMainBinding.ivWeatherIcon.setImageResource(R.drawable.cloudy)
        }

        activityMainBinding.pbLoading.visibility=View.GONE
        activityMainBinding.rlMainLayout.visibility=View.VISIBLE

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