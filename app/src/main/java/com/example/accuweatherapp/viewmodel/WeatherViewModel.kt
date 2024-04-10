package com.example.accuweatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.accuweatherapp.model.WeatherResponse
import com.example.accuweatherapp.repository.Result
import com.example.accuweatherapp.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _weatherResponseLiveData = MutableLiveData<Result<WeatherResponse>>()
    val weatherResponseLiveData: LiveData<Result<WeatherResponse>> get() = _weatherResponseLiveData

    fun fetchWeather(cityName: String, apiKey: String) {
        viewModelScope.launch {
            val result = repository.getWeatherResponse(cityName, apiKey)
            _weatherResponseLiveData.postValue(result.value)
        }
    }
}
