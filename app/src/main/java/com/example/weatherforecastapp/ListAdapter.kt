package com.example.weatherforecastapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecastapp.data.entity.WeatherLocation
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    lateinit var cityListArrayList: ArrayList<WeatherLocation>

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return cityListArrayList.size
    }


    fun getFilter(): Filter? {
        return object : Filter() {
            override fun publishResults(constraint: CharSequence?, results: FilterResults) {
                cityListArrayList = results.values as ArrayList<WeatherLocation> // has
                notifyDataSetChanged()
            }

            override fun performFiltering(constraint: CharSequence?): FilterResults? {
                var constraint = constraint
                val results = FilterResults()
                val FilteredArrList: MutableList<WeatherLocation> = ArrayList<WeatherLocation>()
                if (cityListArrayList == null) {
                    cityListArrayList = ArrayList<WeatherLocation>(cityListArrayList)
                }
                if (constraint == null || constraint.length == 0) {

                    results.count = cityListArrayList.size
                    results.values = cityListArrayList
                } else {
                    val locale: Locale = Locale.getDefault()
                    constraint = constraint.toString().toLowerCase(locale)
                    for (i in 0 until cityListArrayList.size) {
                        val model: WeatherLocation = cityListArrayList.get(i)
                        val data: String = model.name
                        if (data.toLowerCase(locale).contains(constraint.toString())) {
                            FilteredArrList.add(WeatherLocation(name = "", region = "",
                                country = "", lat = 0.0, localtimeEpoch = 2
                                , lon = 0.0, tzId = ""))
                        }
                    }
                    results.count = FilteredArrList.size
                    results.values = FilteredArrList
                }
                return results
            }
        }
    }



}