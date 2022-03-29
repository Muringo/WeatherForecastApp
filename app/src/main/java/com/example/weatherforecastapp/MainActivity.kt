package com.example.weatherforecastapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.weatherforecastapp.data.entity.WeatherLocation
import com.example.weatherforecastapp.fragments.CurrentTemperaturesFragment
import com.example.weatherforecastapp.fragments.CurrentWeatherFragment
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    lateinit var placeSV: SearchView
    lateinit var cityLV: ListView
    lateinit var weatherModelArrayList: ArrayList<WeatherModel>
    lateinit var listAdapter: ListAdapter
    lateinit var locationManager: LocationManager
    lateinit var location: Location
    private val PERMISSION_CODE = 1

    lateinit var currentWeatherFragment: CurrentWeatherFragment
    lateinit var currentTemperaturesFragment: CurrentTemperaturesFragment


    var adapter: ArrayAdapter<WeatherLocation>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        placeSV = findViewById(R.id.sv_places)
        cityLV = findViewById(R.id.lv_city)

        currentWeatherFragment = CurrentWeatherFragment()
        currentTemperaturesFragment = CurrentTemperaturesFragment()

        val fm: FragmentManager = supportFragmentManager
        fm.beginTransaction().replace(R.id.fragment_current_weather, currentWeatherFragment).commit()
        fm.beginTransaction().replace(R.id.fragment_current_temperatures, currentTemperaturesFragment).commit()


        listAdapter = ListAdapter()
        cityLV.setAdapter(adapter)

        fun onCreateOptionsMenu(menu: Menu?): Boolean {

            val menuItem = menu?.findItem(R.id.sv_places)
            val searchMenuItem = menuItem?.actionView

            if (searchMenuItem is SearchView) {
                searchMenuItem.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        menuItem.collapseActionView()
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        listAdapter?.getFilter()?.filter(newText);

                        return false
                    }

                })
            }
            return true
        }


        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                PERMISSION_CODE
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_current_weather)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun getCityName(context: Context, latitude: Double, longitude: Double) {
        var cityName = "Not Found"
        val gcd = Geocoder(baseContext, Locale.getDefault())
        try {
            val addresses: List<Address>? =
                gcd.getFromLocation(latitude, longitude, 1)
            for (adr in addresses!!) {
                if (adr.locality != null && adr.locality.length > 0) {
                    cityName = adr.toString()
                } else {
                    Log.d("TAG", "City not found")
                    Toast.makeText(this, "User city not found...", Toast.LENGTH_SHORT).show()
                }
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}

private fun SearchView.setOnQueryTextListener(mainActivity: MainActivity) {

}
