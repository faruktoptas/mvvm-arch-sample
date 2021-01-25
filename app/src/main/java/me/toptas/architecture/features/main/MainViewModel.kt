package me.toptas.architecture.features.main

import androidx.lifecycle.MutableLiveData
import me.toptas.architecture.base.BaseViewModel
import me.toptas.architecture.common.model.Album
import me.toptas.architecture.data.MainRepository

class MainViewModel(private val repo: MainRepository) : BaseViewModel() {

    val albumsLive = MutableLiveData<List<Album>>()

    fun fetchItems() {
        callService({ repo.getAlbums() }, {
            albumsLive.postValue(it)
        })
    }
}