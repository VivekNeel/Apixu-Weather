package com.weather.apixu.data

import com.weather.apixu.data.remote.CurrentWeather
import com.weather.apixu.data.remote.Location
import org.parceler.Parcel
import org.parceler.ParcelConstructor
import java.util.ArrayList

/**
 * Created by vivek on 04/04/18.
 */

@Parcel(Parcel.Serialization.BEAN)
data class ForecastDay @ParcelConstructor constructor(val maxTemp: String, val minTemp: String, val date: String, val conditionText: String, val conditionIcon: String)

@Parcel(Parcel.Serialization.BEAN)
data class WeatherDetailDTO @ParcelConstructor constructor(val cityName: String, val temp: Double, val forecastDays: ArrayList<ForecastDay>)