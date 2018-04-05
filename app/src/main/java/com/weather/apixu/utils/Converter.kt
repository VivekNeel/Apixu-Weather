package com.weather.apixu.utils

import com.weather.apixu.data.ForecastDay
import com.weather.apixu.data.WeatherDetailDTO
import com.weather.apixu.data.remote.WeatherResponse
import java.util.*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat


/**
 * Created by vivek on 04/04/18.
 */
object Converter {
    fun transformToWeatherDetailsDTO(weatherResponse: WeatherResponse): WeatherDetailDTO {

        val forecastDays = ArrayList<ForecastDay>()
        weatherResponse.forecast.forecastday.forEach { it -> forecastDays.add(ForecastDay(it.day.maxtemp_c.toString() , it.day.mintemp_c.toString() , getFormattedDate(it.date) , it.day.condition.text , it.day.condition.icon)) }
        return WeatherDetailDTO(weatherResponse.location.name, Math.round(weatherResponse.current.temp_c).toString()
                , forecastDays)

    }

    fun getFormattedDate(date: String): String {
        val dateTime = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(date)
        val today = DateTime()
        val tomorrow = today.minusDays(-1)

        return if (dateTime.toLocalDate() == today.toLocalDate()) {
            "Today"
        } else if (dateTime.toLocalDate() == tomorrow.toLocalDate()) {
            "Tomorrow"
        } else {
            date
        }
    }
}