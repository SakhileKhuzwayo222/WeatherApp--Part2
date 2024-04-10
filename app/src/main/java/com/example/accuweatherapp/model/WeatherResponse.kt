package com.example.accuweatherapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WeatherResponse(
    @SerializedName("coord")
    val coord: Coord = Coord(0.0, 0.0),
    @SerializedName("weather")
    val weather: List<Weather> = emptyList(),
    @SerializedName("base")
    val base: String = "",
    @SerializedName("main")
    val main: Main = Main(0.0, 0.0, 0.0, 0.0, 0, 0),
    @SerializedName("visibility")
    val visibility: Int = 0,
    @SerializedName("wind")
    val wind: Wind = Wind(0.0, 0, 0.0),
    @SerializedName("clouds")
    val clouds: Clouds = Clouds(0),
    @SerializedName("dt")
    val dt: Long = 0,
    @SerializedName("sys")
    val sys: Sys = Sys(0, 0, "", 0, 0),
    @SerializedName("timezone")
    val timezone: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("cod")
    val cod: Int = 0
):Serializable
{
    data class Coord(
        @SerializedName("lon")
        val lon: Double,
        @SerializedName("lat")
        val lat: Double
    ):Serializable

    data class Weather(
        @SerializedName("id")
        val id: Int,
        @SerializedName("main")
        val main: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("icon")
        val icon: String
    ):Serializable

    data class Main(
        @SerializedName("temp")
        val temp: Double,
        @SerializedName("feels_like")
        val feels_like: Double,
        @SerializedName("temp_min")
        val temp_min: Double,
        @SerializedName("temp_max")
        val temp_max: Double,
        @SerializedName("pressure")
        val pressure: Int,
        @SerializedName("humidity")
        val humidity: Int
    ):Serializable

    data class Wind(
        @SerializedName("speed")
        val speed: Double,
        @SerializedName("deg")
        val deg: Int,
        @SerializedName("gust")
        val gust: Double
    ):Serializable

    data class Clouds(
        @SerializedName("all")
        val all: Int
    ):Serializable

    data class Sys(
        @SerializedName("type")
        val type: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("country")
        val country: String,
        @SerializedName("sunrise")
        val sunrise: Long,
        @SerializedName("sunset")
        val sunset: Long
    ):Serializable
}

  /*
  {
  "coord": {
    "lon": -123.1193,
    "lat": 49.2497
  },
  "weather": [
    {
      "id": 800,
      "main": "Clear",
      "description": "clear sky",
      "icon": "01d"
    }
  ],
  "base": "stations",
  "main": {
    "temp": 13.05,
    "feels_like": 11.77,
    "temp_min": 11.24,
    "temp_max": 14.43,
    "pressure": 1024,
    "humidity": 52
  },
  "visibility": 10000,
  "wind": {
    "speed": 7.15,
    "deg": 233,
    "gust": 8.49
  },
  "clouds": {
    "all": 9
  },
  "dt": 1712700844,
  "sys": {
    "type": 2,
    "id": 2011597,
    "country": "CA",
    "sunrise": 1712669492,
    "sunset": 1712717746
  },
  "timezone": -25200,
  "id": 6173331,
  "name": "Vancouver",
  "cod": 200
}
  */

