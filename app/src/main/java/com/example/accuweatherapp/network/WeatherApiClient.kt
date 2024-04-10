package com.example.accuweatherapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApiClient {

    private val baseUrl = "https://api.openweathermap.org/data/2.5/"
    val weatherService: WeatherService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        weatherService = retrofit.create(WeatherService::class.java)
    }
}
