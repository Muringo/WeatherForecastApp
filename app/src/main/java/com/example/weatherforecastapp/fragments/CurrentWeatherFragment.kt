package com.example.weatherforecastapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.WeatherAdapter
import com.example.weatherforecastapp.WeatherModel
import com.example.weatherforecastapp.viewmodel.CurrentWeatherViewModel

class CurrentWeatherFragment : ScopedFragment() {

//    override val kodein by closestKodein()
//    private val viewModelFactory: CurrentWeatherViewModelFactory

    private lateinit var viewModel: CurrentWeatherViewModel

    private lateinit var tv_degrees: TextView
    private lateinit var iv_conditon_icon: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.current_weather_fragment, container, false)

        fun updateLocation(location: String) {
            (activity as? AppCompatActivity)?.supportActionBar?.title = location
        }

        fun updateDateToToday() {
            (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"
        }

        fun updateTemperatures(temperature: Double) {
//        val unitAbbreviation = if(viewModel.weather) "°C" else "°F"
            tv_degrees.text = "$temperature"
        }
        return view

    }

//    private fun bindUI() = launch {
//        val currentWeather = viewModel.weather.await()
//
//        currentWeather.observe(this@CurrentWeatherFragment, Observer {
//            if (it == null) return@Observer
//
//            updateDateToToday()
//            updateTemperatures(it.temperature)
//
//
//            GlideApp.with(this@CurrentWeatherFragment)
//                .load("http:${it.conditionIconUrl}")
//                .into(iv_conditon_icon)
//        })
//    }





}

//private fun Any.await() {
//
//}

