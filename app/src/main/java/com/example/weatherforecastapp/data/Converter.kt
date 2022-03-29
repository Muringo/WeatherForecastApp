package com.example.weatherforecastapp.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converter {
    private val gson = Gson()
    private val listTypeConverter = object : TypeToken<List<String>>() {}.type

    @TypeConverter
    @JvmStatic
    fun fromListToIcon(list: List<String>): String = gson.toJson(list, listTypeConverter)

    @TypeConverter
    @JvmStatic
    fun fromIconToList(icon: String): List<String> = gson.fromJson(icon, listTypeConverter)
}