package com.weather.apixu.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.weather.apixu.R
import com.weather.apixu.WRApplication
import com.weather.apixu.data.ForecastDay
import com.weather.apixu.data.WeatherDetailDTO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.weather.apixu.CITY_NAME
import com.weather.apixu.utils.hide
import com.weather.apixu.utils.show
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.content_forcast.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.layout_loader.*


/**
 * Created by vivek on 04/04/18.
 */
class WeatherActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: Factory
    private lateinit var viewModel: WeatherViewModel
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WRApplication.appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel::class.java)

        start()

    }

    private fun start() {
        showLoader()
        val disposable = viewModel.getCurrentWeather(CITY_NAME).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: WeatherDetailDTO? ->
                    run {
                        tempText.text = t!!.temp.toString()
                        cityName.text = t.cityName
                        setupForecastList(t.forecastDays)
                    }
                }, { t: Throwable? -> renderErrorView() })
        compositeDisposable.add(disposable)
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    private fun renderErrorView() {
        hideLoader()
        errorSceneContainer.show()
        retryButton.setOnClickListener {
            kotlin.run {
                errorSceneContainer.hide()
                start()
            }
        }

    }

    private fun setupForecastList(list: ArrayList<ForecastDay>) {
        hideLoader()
        errorSceneContainer.hide()
        weatherViewContainer.show()
        val layoutManager = LinearLayoutManager(applicationContext)
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL)
        forecastList.layoutManager = layoutManager
        forecastList.addItemDecoration(dividerItemDecoration)
        forecastList.setHasFixedSize(true)
        forecastList.adapter = WeatherAdapter(list)

    }

    private fun showLoader() {
        loaderContainer.show()
        val rotateAnim = RotateAnimation(
                0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        )

        rotateAnim.duration = 1000
        rotateAnim.repeatCount = Animation.INFINITE
        loader.startAnimation(rotateAnim)

    }

    private fun hideLoader() {
        loaderContainer.hide()
    }
}