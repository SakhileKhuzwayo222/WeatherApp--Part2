package com.example.accuweatherapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    var searchQuery by mutableStateOf("")

    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
    }
}