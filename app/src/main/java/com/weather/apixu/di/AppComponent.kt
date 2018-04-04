package com.weather.apixu.di

import com.konradszewczuk.weatherapp.di.NetworkModule
import com.weather.apixu.ui.WeatherActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by vivek on 04/04/18.
 */
@Component(modules = arrayOf(AppModule::class, NetworkModule::class, WeatherRepositoryModule::class))
@Singleton
interface AppComponent {

    fun inject(weatherActivity: WeatherActivity)
}