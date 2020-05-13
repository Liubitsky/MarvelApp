package com.denisliubitsky.marvelapp.model.network

import com.denisliubitsky.marvelapp.model.domain.MarvelCharacter
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelWebService {
    @GET("characters")
    suspend fun getCharactersAsync(
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): Response<DataWrapper<List<MarvelCharacter>>>
}