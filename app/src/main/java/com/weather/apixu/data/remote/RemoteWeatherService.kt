package com.weather.apixu.data.remote

import com.weather.apixu.BuildConfig
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by vivek on 04/04/18.
 */
interface RemoteWeatherService {

    @GET("forecast.json?key=${BuildConfig.API_KEY}&days=7")
    fun fetchCurrentWeather(@Query("q") location: String): Single<WeatherResponse>
}