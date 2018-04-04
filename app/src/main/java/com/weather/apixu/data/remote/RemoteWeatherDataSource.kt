package com.weather.apixu.data.remote

import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by vivek on 04/04/18.
 */
class RemoteWeatherDataSource @Inject constructor(private val remoteWeatherService: RemoteWeatherService) {

    fun requestCurrentWeather(cityName: String): Single<WeatherResponse> = remoteWeatherService.fetchCurrentWeather(cityName)
}