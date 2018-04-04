package com.weather.apixu.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by vivek on 04/04/18.
 */
interface RemoteWeatherService {

    // TODO : Move api key to gradle.properties
    @GET("forecast.json?key=55082089de9c4c0281694433180404&days=7")
    fun fetchCurrentWeather(@Query("q") location: String): Single<WeatherResponse>
}