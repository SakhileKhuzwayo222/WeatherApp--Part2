package com.example.accuweatherapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.accuweatherapp.model.WeatherResponse
import com.example.accuweatherapp.network.WeatherApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class WeatherRepository(private val weatherApiClient: WeatherApiClient) {

    suspend fun getWeatherResponse(
        cityName: String,
        apiKey: String
    ): LiveData<Result<WeatherResponse>> {
        val responseLiveData = MutableLiveData<Result<WeatherResponse>>()

        try {
            val response = withContext(Dispatchers.IO) {
                weatherApiClient.weatherService.getCurrentWeather(cityName, apiKey).execute()
            }
            if (response.isSuccessful) {
                val weatherResponse = response.body()
                if (weatherResponse != null) {
                    responseLiveData.value = Result.Success(weatherResponse)
                } else {
                    responseLiveData.value =
                        Result.Error(NullPointerException("Response body is null"))
                }
            } else {
                val errorBody = response.errorBody()?.string() ?: "Unknown Error"
                responseLiveData.value = Result.Error(HttpException(response))
            }
        } catch (e: Exception) {
            responseLiveData.value = Result.Error(e)
        }

        return responseLiveData
    }
}

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

