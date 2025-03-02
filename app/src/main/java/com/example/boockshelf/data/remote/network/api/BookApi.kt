package com.example.boockshelf.data.remote.network.api

import com.example.boockshelf.data.remote.network.response.DtoBook
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {

    @GET("volumes")
    suspend fun bookSearch(
        @Query("q") searchQuery: String,
        @Query("maxResults") maxResults: Int
    ): DtoBook
}