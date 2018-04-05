package com.weather.apixu.data.repository

import com.weather.apixu.data.WeatherDetailDTO
import com.weather.apixu.data.remote.RemoteWeatherDataSource
import com.weather.apixu.data.remote.WeatherResponse
import com.weather.apixu.utils.Converter
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by vivek on 04/04/18.
 */
@Singleton
class WeatherRepositoryImpl @Inject constructor(private val remoteWeatherDataSource: RemoteWeatherDataSource) :
        WeatherRepository {
    override fun getCurrentWeather(cityName: String): Single<WeatherDetailDTO> {
        return remoteWeatherDataSource.requestCurrentWeather(cityName).map { weatherResponse: WeatherResponse ->
            Converter.transformToWeatherDetailsDTO(weatherResponse)
        }
    }
}