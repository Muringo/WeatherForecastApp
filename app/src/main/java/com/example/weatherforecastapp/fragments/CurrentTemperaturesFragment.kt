package com.example.weatherforecastapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecastapp.ListAdapter
import com.example.weatherforecastapp.viewmodel.CurrentTemperaturesViewModel
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.data.entity.WeatherLocation

class CurrentTemperaturesFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentTemperaturesFragment()
    }

    private lateinit var viewModel: CurrentTemperaturesViewModel
    private lateinit var listAdapter: ListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.current_temperatures_fragment, container, false)

        var recyclerView: RecyclerView
        recyclerView = view as RecyclerView
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(this).get(CurrentTemperaturesViewModel::class.java)
        

        return view
    }


}