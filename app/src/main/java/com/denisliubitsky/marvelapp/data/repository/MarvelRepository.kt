package com.denisliubitsky.marvelapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.denisliubitsky.marvelapp.data.paging.CharactersPositionalDataSourceFactory
import com.denisliubitsky.marvelapp.model.domain.MarvelCharacter

interface MarvelRepository {
    fun getDataSourceFactory(): CharactersPositionalDataSourceFactory
}