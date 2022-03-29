package com.example.weatherforecastapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherforecastapp.data.entity.CurrentWeatherEntry
import com.example.weatherforecastapp.data.entity.WeatherLocation


@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert (weatherEntry: CurrentWeatherEntry)

    @Query("SELECT * FROM current_weather")
    fun getAll(): List<CurrentWeatherEntry>

//    @Query("SELECT * FROM weather_location WHERE name LIKE :name")
//    fun findByName(name: String): WeatherLocation
}