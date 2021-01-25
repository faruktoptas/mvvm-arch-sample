package me.toptas.architecture.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import me.toptas.architecture.base.BaseListAdapter
import me.toptas.architecture.common.ext.observeNotNull

fun <T> LiveData<List<T>>.asAdapterItems(owner: LifecycleOwner, adapter: BaseListAdapter<T, *>) {
    observeNotNull(owner, { adapter.setItems(it) })
}