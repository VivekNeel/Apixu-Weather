package com.weather.apixu

import com.weather.apixu.data.remote.*
import com.weather.apixu.data.repository.WeatherRepository
import com.weather.apixu.data.repository.WeatherRepositoryImpl
import com.weather.apixu.utils.Converter
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by vivek on 04/04/18.
 */
@RunWith(MockitoJUnitRunner::class)
class WeatherRepoTest {

    @Mock
    private lateinit var remoteWeatherSource: RemoteWeatherService

    private lateinit var remoteWeatherDataSource : RemoteWeatherDataSource

    private lateinit var repo: WeatherRepository
    @Before
    fun setup() {
        remoteWeatherDataSource = RemoteWeatherDataSource(remoteWeatherSource)
        repo = WeatherRepositoryImpl(remoteWeatherDataSource)
    }

    @Test
    fun testWeatherDTO() {
        val weatherResponse = WeatherResponse(locationMock, weatherMock, forcastDayResponseMock)
        Mockito.`when`(remoteWeatherDataSource.requestCurrentWeather("Bangalore")).thenReturn(Single.just(weatherResponse))
        repo.getCurrentWeather("Bangalore").test().assertNoErrors().assertValue { it == Converter.transformToWeatherDetailsDTO(weatherResponse) }
    }


    companion object {
        val locationMock = Location("Bangalore")
        val weatherMock = CurrentWeather(44.3)
        val conditionMock = Condition("clear", "//cdn.apixu.com/weather/64x64/day/116.png")
        val dayMock = Day(44.3, 33.3, conditionMock)
        val forcastMock = ForecastDay("2018-04-04", dayMock)
        val forcastDayResponseMock = ForecaseDayResponse(arrayListOf(forcastMock))
    }
}