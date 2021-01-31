package me.toptas.architecture.network

import me.toptas.architecture.common.model.Album
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("photos")
    suspend fun getAlbums(): Response<List<Album>>
}