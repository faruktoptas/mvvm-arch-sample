package me.toptas.architecture.common.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun MutableLiveData<Boolean>.postTrue() = postValue(true)

fun MutableLiveData<Boolean>.postFalse() = postValue(false)


fun <T> LiveData<T>.observeNotNull(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    observe(owner, {
        it?.apply {
            observer(this)
        }
    })
}


fun LiveData<Boolean>.observeIfTrue(owner: LifecycleOwner, observer: () -> Unit) {

    observe(owner, {
        if (it == true) observer()
    })
}