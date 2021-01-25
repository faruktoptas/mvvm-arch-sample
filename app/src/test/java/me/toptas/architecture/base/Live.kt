package me.toptas.architecture.base

import androidx.lifecycle.LiveData

fun <T> LiveData<T>.observedValue(): T? {
    observeForever { }
    return value
}