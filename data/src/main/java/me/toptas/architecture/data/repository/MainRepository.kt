package me.toptas.architecture.data.repository

import me.toptas.architecture.common.model.Album
import me.toptas.architecture.common.model.ApiResponse
import me.toptas.architecture.network.Api
import me.toptas.architecture.network.apiWrapper

interface MainRepository {

    suspend fun getAlbums(): ApiResponse<List<Album>>
}

class MainRepositoryImpl(private val api: Api) : MainRepository {

    override suspend fun getAlbums() = apiWrapper { api.getAlbums() }

}