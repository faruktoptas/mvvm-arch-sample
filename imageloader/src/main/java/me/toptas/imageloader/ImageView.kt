package me.toptas.imageloader

import android.widget.ImageView
import coil.load

fun ImageView.loadImage(url: String?) {
    url?.let {
        load(it)
    }
}