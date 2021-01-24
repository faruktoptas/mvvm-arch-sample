package me.toptas.architecture.network

import me.toptas.architecture.model.Album
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("albums")
    suspend fun getAlbums(): Response<List<Album>>
}