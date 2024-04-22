package com.example.accuweatherapp.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class WeatherType(val advise: String,val items:String="Sunscreen\nSunglasses\nWaterBottle\nHat") {
    CLEAR("You can enjoy CLEAR weather. Wear smart dress, sunglasses, and sunscreen, and get ready for some delightful outdoor activities under the bright sun. Perfect for picnics, hikes, or a leisurely day at the beach.", "Sunscreen\nSunglasses\nWaterBottle\nHat"),
    THUNDERSTORM("You can enjoy THUNDERSTORM weather. It's best to stay indoors and be safe. Cozy up with a good book or movie while listening to the soothing sound of raindrops. A great opportunity for some indoor relaxation.", "Umbrella\nFlashlight\nBattery-powered radio\nEmergency kit"),
    DRIZZLE("You can enjoy DRIZZLE weather. It's a good time for a cozy indoor activity. Whether it's baking cookies, watching movies, or simply lounging with a hot cup of tea, drizzly weather sets the perfect ambiance for indoor comfort.","Umbrella\nRaincoat\nShoes\nCap"),
    CLOUDS("You can enjoy CLOUDY weather. Perfect for a leisurely stroll. Embrace the cool breeze and soft light filtering through the clouds as you take a tranquil walk in nature. Ideal for reflection and peaceful moments.", "Umbrella\nRaincoat\nShoes\nCap"),
    CLOUDS_SCATTERED_CLOUDS("You can enjoy SCATTERED CLOUDS weather. Perfect for outdoor activities. With patches of blue sky peeking through the clouds, seize the day for outdoor adventures like cycling, gardening, or a picnic in the park.","Umbrella\nRaincoat\nShoes\nCap"),
    CLOUDS_BROKEN_CLOUDS("You can enjoy BROKEN CLOUDS weather. A good time for a picnic. The intermittent sunshine and passing clouds create a picturesque scene for a picnic with friends or family. Don't forget to pack your favorite snacks!","Umbrella\nRaincoat\nShoes\nCap"),
    CLOUDS_FEW_CLOUDS("You can enjoy FEW CLOUDS weather. Ideal for outdoor sports. With the sun shining through sparse clouds, it's the perfect weather for outdoor sports like soccer, basketball, or a friendly game of frisbee in the park.","Umbrella\nRaincoat\nShoes\nCap"),
    RAIN("You can enjoy RAINY weather. Don't forget your umbrella! Rainy days offer a cozy atmosphere for indoor activities like board games, baking, or curling up with a good book. Enjoy the rhythmic pitter-patter of raindrops outside.", "Umbrella\nRaincoat\nShoes\nCap"),
    RAIN_FREEZING_RAIN("You can enjoy FREEZING RAIN weather. Stay indoors if possible. Freezing rain can create hazardous conditions, so it's best to stay warm and cozy indoors until the weather improves. Perfect for a movie marathon or crafting session at home.", "Umbrella\nRaincoat\nShoes\nCap"),
    RAIN_VERY_HEAVY_RAIN("You can enjoy VERY HEAVY RAIN weather. Best to postpone outdoor plans. Heavy rain can lead to flooding and dangerous road conditions, so it's safest to stay indoors until the storm passes. Use this time to catch up on indoor tasks or enjoy a relaxing day at home.", "Umbrella\nRaincoat\nShoes\nCap"),
    RAIN_HEAVY_INTENSITY("You can enjoy HEAVY INTENSITY RAIN weather. Be cautious while driving. Heavy rain can reduce visibility and increase the risk of accidents on the road. If you must travel, drive carefully and use caution. Otherwise, it's a great excuse to stay home and unwind.", "Umbrella\nRaincoat\nShoes\nCap"),
    RAIN_MODERATE_RAIN("You can enjoy MODERATE RAIN weather. Grab your raincoat and enjoy a walk. Moderate rain offers a refreshing opportunity to go for a walk and enjoy the fresh air. Don your raincoat and embrace the invigorating experience of a rainy day stroll.", "Umbrella\nRaincoat\nShoes\nCap"),
    RAIN_LIGHT_RAIN("You can enjoy LIGHT RAIN weather. A refreshing time for a short walk. Light rain showers provide a refreshing break from the heat and offer the perfect excuse to take a leisurely stroll outdoors. Enjoy the gentle patter of raindrops as you explore your surroundings.", "Umbrella\nRaincoat\nShoes\nCap"),
    SNOW("You can enjoy SNOWY weather. Get ready for a snowball fight! Snowy days bring joy and excitement, especially for children eager to build snowmen and engage in friendly snowball fights. Bundle up in warm clothing and embrace the winter wonderland.", "Winter coat\nBoots\nGloves\nScarf"),
    SNOW_LIGHT_SNOW("You can enjoy LIGHT SNOW weather. Perfect for building a snowman. Light snowfall transforms the landscape into a magical winter scene, ideal for building snowmen, creating snow angels, or simply admiring the serene beauty of nature blanketed in snow.", "Winter coat\nBoots\nGloves\nScarf"),
    MIST("You can enjoy MISTY weather. It creates a mystical atmosphere. Misty weather lends an enchanting ambiance to the surroundings, evoking a sense of mystery and tranquility. Take a leisurely walk in the misty air and immerse yourself in the serene beauty of the mist-covered landscape.", "Umbrella\nJacket\nComfortable shoes"),
}
 object MyUtils{
    fun convertKelvinToCelsius(kelvin: Double?): Int {
        kelvin?.let {
            val celsius = it - 273.15
            return celsius.toInt()
        }
        return 0
    }

    fun convertUnixTimestampToTime(timestamp: Long): String {
        val milliseconds = timestamp * 1000
        val date = Date(milliseconds)
        val format = SimpleDateFormat("hh:mm", Locale.getDefault())
        return format.format(date)
    }

}
object Constants {
    const val API_ID = "845b9326ad240a51c983493d75bf120b"
    const val CITY_NAME = "Vancouver,CA"
}

