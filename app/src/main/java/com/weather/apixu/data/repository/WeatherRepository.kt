package com.weather.apixu.data.repository

import com.weather.apixu.data.WeatherDetailDTO
import io.reactivex.Single

/**
 * Created by vivek on 04/04/18.
 */
interface WeatherRepository {
    fun getCurrentWeather(cityName :String): Single<WeatherDetailDTO>
}