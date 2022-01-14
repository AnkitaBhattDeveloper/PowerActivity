package com.example.poweractivity

import com.example.poweractivity.data.PriceItemModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface SignupApi {
    @GET("signup")
  fun signupData():Call<ResponseBody>

    

}

//fun getCurrentWeatherData(@Query("lat") lat: String, @Query("lon") lon: String,
// @Query("APPID") app_id: String): Call<WeatherResponse>

