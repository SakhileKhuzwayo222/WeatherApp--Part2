package com.example.accuweatherapp.network

import com.example.accuweatherapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
  fun getCurrentWeather(@Query("q") cityName: String, @Query("appid") apiKey: String): Call<WeatherResponse>
}

