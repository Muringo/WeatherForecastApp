package com.example.weatherforecastapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherforecastapp.viewmodel.CurrentWeekWeatherViewModel
import com.example.weatherforecastapp.R

class CurrentWeekWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentWeekWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeekWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_week_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CurrentWeekWeatherViewModel::class.java)
        // TODO: Use the ViewModel
    }

}