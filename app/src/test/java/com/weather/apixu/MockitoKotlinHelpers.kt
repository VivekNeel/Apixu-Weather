package com.weather.apixu

import org.mockito.ArgumentCaptor

/**
 * Created by vivek on 04/04/18.
 */
fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()