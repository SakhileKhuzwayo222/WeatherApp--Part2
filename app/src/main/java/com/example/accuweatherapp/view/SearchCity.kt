package com.example.accuweatherapp.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.accuweatherapp.util.AccuWeatherAppTheme
import com.example.accuweatherapp.util.darkBlue
import com.example.accuweatherapp.viewmodel.SearchViewModel

class SearchCity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccuWeatherAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Search()
                }
            }
        }
    }
}


@Composable
fun Search() {

    val background = Brush.verticalGradient(listOf(Color.Blue, darkBlue))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(viewModel = SearchViewModel())
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(viewModel: SearchViewModel) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var modifier: Modifier = Modifier

    Surface(
        shape = SearchBarDefaults.dockedShape,
        modifier = modifier.height(72.dp)
    ) {
        SearchBar(
            modifier = modifier,
            query = viewModel.searchQuery,
            onQueryChange = {
                viewModel.onSearchQueryChange(it)
            },
            onSearch = {
                keyboardController?.hide()
                //call findCities(it)
                val intent = Intent(context, WeatherActivity::class.java)
                intent.putExtra("city_name", it)
                context.startActivity(intent)
                (context as Activity).finish()
            },
            placeholder = {
                Text(text = "Search City")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null
                )
            },
            trailingIcon = {
                if (viewModel.searchQuery.isNotEmpty()) {
                    IconButton(onClick = { viewModel.onSearchQueryChange("") }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            tint = MaterialTheme.colorScheme.onSurface,
                            contentDescription = "Clear search"
                        )
                    }
                }

            },
            content = {
            },
            active = true,
            onActiveChange = {},
            tonalElevation = 0.dp
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrevSearchCity() {
    AccuWeatherAppTheme {
        Surface {
            Search()
        }
    }
}







