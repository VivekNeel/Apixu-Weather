package com.weather.apixu.data.remote

/**
 * Created by vivek on 04/04/18.
 */

data class CurrentWeather(var temp_c: Double)

data class Location(val name: String)

data class Condition(val text: String, val icon: String)

data class Day(val maxtemp_c: Double, val mintemp_c: Double,val condition: Condition)

data class ForecastDay(val date : String ,val day: Day )

data class ForecaseDayResponse(val forecastday : ArrayList<ForecastDay>)

data class WeatherResponse(val location: Location, val current: CurrentWeather, val forecast: ForecaseDayResponse)