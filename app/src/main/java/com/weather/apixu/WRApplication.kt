package com.weather.apixu

import android.app.Application
import com.konradszewczuk.weatherapp.di.NetworkModule
import com.weather.apixu.di.AppComponent
import com.weather.apixu.di.AppModule
import com.weather.apixu.di.DaggerAppComponent

/**
 * Created by vivek on 04/04/18.
 */
class WRApplication : Application() {



    companion object {
        lateinit var appComponent : AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).networkModule(NetworkModule()).build()
    }
}