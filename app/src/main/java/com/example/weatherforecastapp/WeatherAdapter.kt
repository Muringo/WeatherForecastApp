package com.example.weatherforecastapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.model.Model
import com.example.weatherforecastapp.data.entity.WeatherLocation
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList


class WeatherAdapter(
    var context: Context,
    var weatherModelArrayList: ArrayList<WeatherModel>
) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val TimeTV: TextView = itemView.findViewById(R.id.idTvTime)
        val TemperatureTV: TextView = itemView.findViewById(R.id.idTvTemperature)
        val WindSpeedTV: TextView= itemView.findViewById(R.id.idTvWindSpeed)
        val ConditionIV: ImageView = itemView.findViewById(R.id.idIvCondition)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = weatherModelArrayList[position]

        holder.TimeTV.setText(model.time)
        holder.TemperatureTV.setText(model.temperature + "°c")
        holder.WindSpeedTV.setText(model.windSpeed + "Km/h")

        Picasso.get().load(model.icon).resize(100, 100).into(holder.ConditionIV)

    }

    override fun getItemCount(): Int {
        return weatherModelArrayList.size
    }


}