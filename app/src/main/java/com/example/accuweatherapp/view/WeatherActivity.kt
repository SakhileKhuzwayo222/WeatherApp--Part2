package com.example.accuweatherapp.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.accuweatherapp.R
import com.example.accuweatherapp.model.ItemData
import com.example.accuweatherapp.model.WeatherResponse
import com.example.accuweatherapp.network.WeatherApiClient
import com.example.accuweatherapp.repository.Result
import com.example.accuweatherapp.repository.WeatherRepository
import com.example.accuweatherapp.util.AccuWeatherAppTheme
import com.example.accuweatherapp.util.MyUtil
import com.example.accuweatherapp.util.Typography
import com.example.accuweatherapp.util.WeatherType
import com.example.accuweatherapp.util.darkBlue
import com.example.accuweatherapp.viewmodel.WeatherViewModel
import com.example.accuweatherapp.viewmodel.WeatherViewModelFactory
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class WeatherActivity : ComponentActivity() {

    private var cityName: String = MyUtil.CITY_NAME.convertToString()
    private val apiKey:String = MyUtil.API_ID.convertToString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent != null) {
            if (intent.getStringExtra("city_name")!=null) {
                cityName = intent.getStringExtra("city_name").toString()
            }
        }
        val weatherViewModel: WeatherViewModel by viewModels {
            WeatherViewModelFactory(WeatherRepository(WeatherApiClient()))
        }
        setContent {
            AccuWeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    // State to track whether weather data is loading
                    var isLoading by remember {
                        mutableStateOf(true)
                    }
                    LaunchedEffect(Unit) {
                        weatherViewModel.fetchWeather(cityName, apiKey)
                        // Update loading state when data fetching is completed
                        delay(2000)
                        isLoading = false
                    }
                    // Show progress indicator if data is loading
                    if (isLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.Blue),
                            contentAlignment = Alignment.Center
                        ) {
                            Surface(color = Color.Blue) {
                                CircularProgressIndicator(
                                    color = Color.Magenta,
                                    strokeWidth = 10.dp
                                )
                            }
                        }
                    } else {
                        WeatherScreen(weatherViewModel)
                    }

                }
            }
        }
    }


    @Composable
    fun WeatherScreen(weatherViewModel: WeatherViewModel) {
        val weatherResponseState by weatherViewModel.weatherResponseLiveData.observeAsState()
        var weatherResponse: WeatherResponse? = null

        // Handle the different states of the weatherResponseState
        when (val result = weatherResponseState) {
            is Result.Success -> {
                weatherResponse = result.data
            }

            is Result.Error -> {
                val errorMessage = result.exception.message ?: "Unknown error"
                Log.e("<2", errorMessage)
            }

            null -> {
                Log.e("<3", "loading")
            }
        }
        val Weather_type: String? = weatherResponse?.weather?.get(0)?.main?.uppercase()
        val context = LocalContext.current
        val background = Brush.verticalGradient(listOf(Color.Blue, darkBlue))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 0.dp)
                .background(background)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 10.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(R.drawable.plus),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                context.startActivity(Intent(context, SearchCity::class.java))
                                (context as Activity)
                            },
                        alignment = Alignment.Center
                    )
                    Text(
                        text = "Mausam",
                        color = Color.White,
                        style = Typography.titleLarge,
                        textAlign = TextAlign.End, modifier = Modifier
                            .padding(5.dp)
                    )
                    Image(
                        painter = painterResource(R.drawable.align),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp), alignment = Alignment.Center
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = weatherResponse?.name ?: "City",
                        color = Color.White,
                        style = Typography.headlineSmall,
                        textAlign = TextAlign.Center, modifier = Modifier
                            .padding(5.dp)
                    )
                    Text(
                        text = "${convertKelvinToCelsius(weatherResponse?.main?.temp)} °C",
                        color = Color.White,
                        style = Typography.displayLarge,
                        textAlign = TextAlign.Center, modifier = Modifier
                            .padding(5.dp)
                    )
                    weatherResponse?.weather?.get(0)?.let {
                        Text(
                            text = toTitleCase(it.description),
                            color = Color.White,
                            style = Typography.headlineLarge,
                            textAlign = TextAlign.Center, modifier = Modifier
                                .padding(5.dp)
                        )
                    } ?: "Sunny"
                }
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Surface(shape = MaterialTheme.shapes.extraLarge, shadowElevation = 0.5.dp) {
                            Text(
                                text = "MIN: ${convertKelvinToCelsius(weatherResponse?.main?.temp_min)} °C",
                                modifier = Modifier.padding(all = 8.dp),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                        Surface(shape = MaterialTheme.shapes.extraLarge, shadowElevation = 0.5.dp) {
                            Text(
                                text = "Max: ${convertKelvinToCelsius(weatherResponse?.main?.temp_max)} °C",
                                modifier = Modifier.padding(all = 8.dp),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                        Surface(shape = MaterialTheme.shapes.extraLarge, shadowElevation = 0.5.dp) {
                            Text(
                                text = "Feels like: ${convertKelvinToCelsius(weatherResponse?.main?.feels_like)} °C",
                                modifier = Modifier.padding(all = 8.dp),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }

                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column {
                            Text(
                                text = "Wind",
                                color = Color.Gray,
                                style = Typography.bodyLarge,
                                textAlign = TextAlign.Center, modifier = Modifier
                            )
                            Text(
                                text = "${weatherResponse?.wind?.speed} mph",
                                color = Color.White,
                                style = Typography.bodyLarge,
                                textAlign = TextAlign.Center, modifier = Modifier
                            )
                        }
                        Column {
                            Text(
                                text = "Rain",
                                color = Color.Gray,
                                style = Typography.bodyLarge,
                                textAlign = TextAlign.Center, modifier = Modifier
                            )
                            Text(
                                text = "${weatherResponse?.clouds?.all} %",
                                color = Color.White,
                                style = Typography.bodyLarge,
                                textAlign = TextAlign.Center, modifier = Modifier
                            )
                        }
                        Column {
                            Text(
                                text = "Humidity",
                                color = Color.Gray,
                                style = Typography.bodyLarge,
                                textAlign = TextAlign.Center, modifier = Modifier
                            )
                            Text(
                                text = "${weatherResponse?.main?.humidity} %",
                                color = Color.White,
                                style = Typography.bodyLarge,
                                textAlign = TextAlign.Center, modifier = Modifier
                            )
                        }


                    }


                }


                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Surface(
                            shape = MaterialTheme.shapes.small,
                            shadowElevation = 0.5.dp,
                            modifier = Modifier.wrapContentSize()
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Image(
                                    painter = painterResource(id = R.drawable.sunrise),
                                    contentDescription = "Sunrise"
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = convertUnixTimestampToTime(
                                        weatherResponse?.sys?.sunrise ?: 0
                                    ),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "AM",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }

                        }
                        Surface(
                            shape = MaterialTheme.shapes.small,
                            shadowElevation = 0.5.dp,
                            modifier = Modifier.wrapContentSize()
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Image(
                                    painter = painterResource(id = R.drawable.sunset),
                                    contentDescription = "Sunset"
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = convertUnixTimestampToTime(
                                        weatherResponse?.sys?.sunset ?: 0
                                    ),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "PM",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }

                        }
                        Surface(
                            shape = MaterialTheme.shapes.small,
                            shadowElevation = 0.5.dp,
                            modifier = Modifier.wrapContentSize()
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Image(
                                    painter = painterResource(id = R.drawable.wind),
                                    contentDescription = "wind"
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "${weatherResponse?.wind?.speed}",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "mph",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }

                        }
                        Surface(
                            shape = MaterialTheme.shapes.small,
                            shadowElevation = 0.5.dp,
                            modifier = Modifier.wrapContentSize()
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Image(
                                    painter = painterResource(id = R.drawable.pressure),
                                    contentDescription = "pressure"
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "${weatherResponse?.main?.pressure}",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "mbi",
                                    style = MaterialTheme.typography.bodyLarge,
                                )
                            }

                        }


                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.Start
                ) {
                    Row {
                        Text(
                            text = "Travel advisory for ",
                            color = Color.White,
                            style = Typography.bodyLarge

                        )
                        weatherResponse?.let {
                            Text(
                                text = it.name, color = Color.White, style = Typography.bodyLarge

                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = WeatherType.valueOf(Weather_type ?: "CLEAR").advise,
                        color = Color.White, style = Typography.bodyMedium

                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Essentials to carry",
                        color = Color.White,
                        style = Typography.bodyLarge,
                        textAlign = TextAlign.Center, modifier = Modifier
                    )

                }
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start
                ) {
                    val itemss = getEssentials(WeatherType.valueOf(Weather_type ?: "CLEAR").items)

                    itemss?.let {
                        val items = listOf(
                            ItemData(R.drawable.dress, it.getOrNull(0).toString(), Color.Cyan),
                            ItemData(R.drawable.dress, it.getOrNull(1).toString(), Color.Green),
                            ItemData(R.drawable.dress, it.getOrNull(2).toString(), Color.Red),
                            ItemData(R.drawable.dress, it.getOrNull(3).toString(), Color.Magenta)
                        )

                        // Calculate the number of items that can fit into one row
                        val itemsPerRow =3

                        // Calculate the number of rows needed
                        val numRows = (items.size + itemsPerRow - 1) / itemsPerRow

                        // Loop through each row
                        repeat(numRows) { rowIndex ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(all = 20.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                // Calculate the start and end index for the current row
                                val start = rowIndex * itemsPerRow
                                val end = minOf((rowIndex + 1) * itemsPerRow, items.size)

                                // Loop through the items for the current row
                                for (index in start until end) {
                                    val item = items[index]
                                    Surface(
                                        shape = MaterialTheme.shapes.extraLarge,
                                        color = item.backgroundColor,
                                        shadowElevation = 0.5.dp,
                                        modifier = Modifier.wrapContentSize()
                                    ) {
                                        Column(modifier = Modifier.padding(10.dp)) {
                                            Image(
                                                painter = painterResource(id = item.imageResource),
                                                contentDescription = null, // Update content description as needed
                                                modifier = Modifier.size(40.dp) // Adjust image size as needed
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))
                                            Text(
                                                text = item.text,
                                                style = MaterialTheme.typography.bodyMedium,
                                                modifier = Modifier.padding(horizontal = 4.dp) // Adjust padding as needed
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    fun convertKelvinToCelsius(kelvin: Double?): Int {
        kelvin?.let {
            val celsius = it - 273.15
            return celsius.toInt()
        }
        return 0 // or any default value you prefer
    }

    fun convertUnixTimestampToTime(timestamp: Long): String {
        // Convert UNIX timestamp to milliseconds
        val milliseconds = timestamp * 1000
        // Create a Date object
        val date = Date(milliseconds)
        // Format the date to a human-readable time format
        val format = SimpleDateFormat("hh:mm", Locale.getDefault())
        return format.format(date)
    }
    fun toTitleCase(input: String): String {
        return input.split(" ").joinToString(" ") { word ->
            word.replaceFirstChar { it.uppercase() }
        }
    }
    fun getEssentials(input: String): List<String> {
       val list = input.split("\n").toList()
        return list
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        AccuWeatherAppTheme {
            Surface {
                WeatherScreen(weatherViewModel = WeatherViewModel(WeatherRepository(WeatherApiClient())))
            }
        }
    }
}
