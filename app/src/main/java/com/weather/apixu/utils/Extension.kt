package com.weather.apixu.utils

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by vivek on 04/04/18.
 */
fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun ImageView.load(url: String) {
    Picasso.get().load(url).into(this)
}