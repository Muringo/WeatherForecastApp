package com.example.weatherforecastapp.data.repository

import android.location.LocationProvider
import android.os.Build
import androidx.lifecycle.LiveData
import com.example.weatherforecastapp.data.db.CurrentWeatherDao
import com.example.weatherforecastapp.data.entity.CurrentWeatherEntry
import com.example.weatherforecastapp.data.entity.CurrentWeatherResponse
import com.example.weatherforecastapp.data.network.WeatherNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class ForecastRepositoryImpl(
    private val weatherNetworkDataSource: WeatherNetworkDataSource,
    private val locationProvider: LocationProvider,


    private val currentWeatherDao: CurrentWeatherDao,
    val getAll: List<CurrentWeatherEntry> = currentWeatherDao.getAll()

) : ForecastRepository {


    init {
        weatherNetworkDataSource.apply {
            downloadedCurrentWeather.observeForever { newCurrentWeather ->
                persistFetchedCurrentWeather(newCurrentWeather)
            }

        }
    }

    override suspend fun getCurrentWeather(): LiveData<List<CurrentWeatherEntry>> {
        TODO("Not yet implemented")
    }


    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            fetchedWeather.currentWeatherEntry?.let { currentWeatherDao.upsert(it) }
        }
    }

    private suspend fun initWeatherData() {

    }


    private suspend fun fetchCurrentWeather() {
        weatherNetworkDataSource.fetchCurrentWeather(
            locationProvider.toString()
        )
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ZonedDateTime.now().minusMinutes(30)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }

}