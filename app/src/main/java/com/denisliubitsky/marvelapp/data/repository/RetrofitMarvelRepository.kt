package com.denisliubitsky.marvelapp.data.repository

import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.denisliubitsky.marvelapp.data.paging.CharactersPositionalDataSourceFactory
import com.denisliubitsky.marvelapp.model.network.MarvelWebService
import com.denisliubitsky.marvelapp.model.domain.MarvelCharacter
import com.denisliubitsky.marvelapp.network.ApiClient
import retrofit2.Retrofit

class RetrofitMarvelRepository() : MarvelRepository {
    private val webService = ApiClient.createService(MarvelWebService::class.java)

    override fun getDataSourceFactory(): CharactersPositionalDataSourceFactory {
        return CharactersPositionalDataSourceFactory(webService)
    }
}
