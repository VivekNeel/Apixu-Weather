package com.weather.apixu.di

import android.content.Context
import com.weather.apixu.WRApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by vivek on 04/04/18.
 */
@Module
class AppModule (private val context : WRApplication) {

    @Provides
    @Singleton
    fun provideContext() : Context  = context.applicationContext
}