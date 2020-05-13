package com.denisliubitsky.marvelapp.data.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.denisliubitsky.marvelapp.model.domain.MarvelCharacter
import com.denisliubitsky.marvelapp.model.network.MarvelWebService
import kotlinx.coroutines.CoroutineScope

class CharactersPositionalDataSourceFactory(private val webService: MarvelWebService): DataSource.Factory<Int, MarvelCharacter>() {
    val source = MutableLiveData<CharactersPositionalDataSource>()

    override fun create(): DataSource<Int, MarvelCharacter> {
        val src = CharactersPositionalDataSource(webService)
        this.source.postValue(src)
        return src
    }
}