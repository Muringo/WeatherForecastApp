package com.example.weatherforecastapp.data.entity


import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    val request: Request,
    val location: WeatherLocation? = null,
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry? = null
)