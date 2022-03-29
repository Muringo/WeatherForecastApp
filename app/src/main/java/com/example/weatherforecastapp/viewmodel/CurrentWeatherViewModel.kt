package com.example.weatherforecastapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.weatherforecastapp.data.entity.CurrentWeatherEntry
import com.example.weatherforecastapp.data.repository.ForecastRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class CurrentWeatherViewModel(
    private var forecastRepository: ForecastRepository
) : ViewModel() {

    lateinit var getAll: LiveData<List<CurrentWeatherEntry>>


    val weather = liveData {
        val task = viewModelScope.async(Dispatchers.IO) {
            forecastRepository.getCurrentWeather()

        }
        emit(task.await())
    }
}