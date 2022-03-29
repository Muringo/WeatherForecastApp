package com.example.weatherforecastapp.data.repository

import androidx.lifecycle.LiveData
import com.example.weatherforecastapp.data.entity.CurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(): LiveData<List<CurrentWeatherEntry>>

}