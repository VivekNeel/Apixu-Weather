package com.weather.apixu.ui

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.weather.apixu.R
import com.weather.apixu.data.ForecastDay
import kotlinx.android.synthetic.main.list_item_forecast.view.*
import android.view.animation.AnimationUtils
import com.weather.apixu.utils.load


/**
 * Created by vivek on 04/04/18.
 */
class WeatherAdapter(private val forcastList: ArrayList<ForecastDay>) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    private var lastPosition = -1

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        with(holder.itemView) {
            if (position > lastPosition) {
                val animation = AnimationUtils.loadAnimation(context, R.anim.list_item_slide_animation)
                holder.itemView.startAnimation(animation)
                lastPosition = position
            }
            val singleDayItem = forcastList[holder.adapterPosition]
            dateText.text = singleDayItem.date
            holder.itemView.conditionIcon.load("https:${singleDayItem.conditionIcon}")
            conditionTemp.text = """${singleDayItem.maxTemp}°/${singleDayItem.minTemp}°"""

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_forecast, parent, false)
        return WeatherViewHolder(itemView)
    }

    override fun getItemCount(): Int =
            forcastList.count()


    class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view)
}