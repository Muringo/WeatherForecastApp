package com.example.weatherforecastapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherforecastapp.data.Converter
import com.example.weatherforecastapp.data.entity.CurrentWeatherEntry
import org.kodein.di.KodeinProperty

@TypeConverters(Converter::class)

@Database(entities = [CurrentWeatherEntry::class], version = 1, exportSchema = false)
abstract class ForecastDatabase : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao

    companion object {
        @Volatile
        private var instance: ForecastDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ForecastDatabase::class.java, "futureWeatherEntries.db"
            )
                .build()


    }

}