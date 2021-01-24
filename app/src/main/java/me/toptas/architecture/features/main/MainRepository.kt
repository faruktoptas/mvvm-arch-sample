package me.toptas.architecture.features.main

import me.toptas.architecture.model.Album
import me.toptas.architecture.model.ApiResponse
import me.toptas.architecture.network.Api
import me.toptas.architecture.network.apiWrapper

interface MainRepository {

    suspend fun getAlbums(): ApiResponse<List<Album>>
}

class MainRepositoryImpl(private val api: Api) : MainRepository {

    override suspend fun getAlbums() = apiWrapper { api.getAlbums() }

}