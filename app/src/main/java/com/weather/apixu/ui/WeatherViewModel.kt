package com.weather.apixu.ui

import android.arch.lifecycle.ViewModel
import com.weather.apixu.data.repository.WeatherRepository
import javax.inject.Inject

/**
 * Created by vivek on 04/04/18.
 */
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) : ViewModel() {

    fun getCurrentWeather(name: String) = weatherRepository.getCurrentWeather(name)
}