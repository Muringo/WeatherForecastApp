package com.example.weatherforecastapp.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherforecastapp.ApiService
import com.example.weatherforecastapp.data.entity.CurrentWeatherResponse
import com.example.weatherforecastapp.data.internal.NoConnectivityException

const val FORECAST_DAYS_COUNT = 4

class WeatherNetworkDataSourceImpl(
    private val ApiService: ApiService
) : WeatherNetworkDataSource {

    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String) {
        try {
            val fetchedCurrentWeather = ApiService
                .getCurrentWeather(location)
                .await()
            _downloadedCurrentWeather.postValue(fetchedCurrentWeather)
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }

    override suspend fun fetchFutureWeather(location: String) {
        TODO("Not yet implemented")
    }

}