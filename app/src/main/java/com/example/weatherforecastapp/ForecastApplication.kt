package com.example.weatherforecastapp

import android.app.Application
import com.example.weatherforecastapp.data.db.ForecastDatabase
import com.example.weatherforecastapp.data.network.NetworkInterceptor
import com.example.weatherforecastapp.data.network.NetworkInterceptorImpl
import com.example.weatherforecastapp.data.network.WeatherNetworkDataSource
import com.example.weatherforecastapp.data.network.WeatherNetworkDataSourceImpl
import com.example.weatherforecastapp.data.repository.ForecastRepository
import com.example.weatherforecastapp.data.repository.ForecastRepositoryImpl
import com.example.weatherforecastapp.viewmodel.CurrentWeatherViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ForecastApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind() from singleton { ForecastDatabase(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
        bind<NetworkInterceptor>() with singleton { NetworkInterceptorImpl(instance()) }
        bind() from singleton { ApiService(instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(), instance(), instance()) }


    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }

}
