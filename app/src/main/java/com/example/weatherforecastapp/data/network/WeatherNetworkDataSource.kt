package com.example.weatherforecastapp.data.network

import androidx.lifecycle.LiveData
import com.example.weatherforecastapp.data.entity.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
    )
    suspend fun fetchFutureWeather(
        location: String
    )
}