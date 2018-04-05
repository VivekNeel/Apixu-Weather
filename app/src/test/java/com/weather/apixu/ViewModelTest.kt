package com.weather.apixu

import com.weather.apixu.data.WeatherDetailDTO
import com.weather.apixu.data.repository.WeatherRepository
import com.weather.apixu.ui.WeatherViewModel
import com.weather.apixu.utils.Converter
import io.reactivex.Single
import junit.framework.Assert
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by vivek on 04/04/18.
 */
@RunWith(MockitoJUnitRunner::class)
class ViewModelTest {

    @Mock
    private lateinit var weatherRepo: WeatherRepository
    @Captor
    private lateinit var nameArgumentCaptor: ArgumentCaptor<String>
    private lateinit var viewModel: WeatherViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = WeatherViewModel(weatherRepo)

    }

    @Test
    fun testFormatDate() {
        val currentDate = DateTime().toLocalDate()
        Assert.assertEquals("Today", Converter.getFormattedDate(currentDate.toString("yyyy-MM-dd")))
        Assert.assertEquals("Tomorrow", Converter.getFormattedDate(currentDate.minusDays(-1).toString("yyyy-MM-dd")))
        Assert.assertEquals(currentDate.minusDays(-2).toString("yyyy-MM-dd"), Converter.getFormattedDate(currentDate.minusDays(-2).toString("yyyy-MM-dd")))
    }

    @Test
    fun testGetCurrentWeather_fetchCurrentWeatherFromRepo() {
        val currentWeatherDetailDTO: WeatherDetailDTO = WeatherDetailDTO("Bangalore", "32.0", arrayListOf())
        Mockito.`when`(weatherRepo.getCurrentWeather("Bangalore")).thenReturn(Single.just(currentWeatherDetailDTO))
        viewModel.getCurrentWeather("Bangalore")
        Mockito.verify<WeatherRepository>(weatherRepo).getCurrentWeather(capture(nameArgumentCaptor))
        Assert.assertEquals("Bangalore", nameArgumentCaptor.value)
    }
}