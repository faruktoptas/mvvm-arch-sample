package me.toptas.architecture.features.main

import androidx.lifecycle.MutableLiveData
import me.toptas.architecture.base.BaseViewModel
import me.toptas.architecture.common.model.Album

class MainViewModel(private val repo: MainRepository) : BaseViewModel() {

    val albumsLive = MutableLiveData<List<Album>>()

    fun fetchItems() {
        callService({ repo.getAlbums() }, {
            albumsLive.postValue(it)
        })
    }
}