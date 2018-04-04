package com.weather.apixu.di

import com.weather.apixu.data.repository.WeatherRepository
import com.weather.apixu.data.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by vivek on 04/04/18.
 */
@Module
abstract class WeatherRepositoryModule {
    @Binds
    abstract fun bindsWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}