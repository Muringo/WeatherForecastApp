package com.example.weatherforecastapp

import android.os.Parcel
import android.os.Parcelable

data class WeatherModel(

    val time: String? = null,
    val temperature: String? = null,
    val icon: String? = null,
    val windSpeed: String? = null,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "WeatherModel(time='$time', temperature='$temperature', icon='$icon', windSpeed='$windSpeed')"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(time)
        parcel.writeString(temperature)
        parcel.writeString(icon)
        parcel.writeString(windSpeed)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherModel> {
        override fun createFromParcel(parcel: Parcel): WeatherModel {
            return WeatherModel(parcel)
        }

        override fun newArray(size: Int): Array<WeatherModel?> {
            return arrayOfNulls(size)
        }
    }
}