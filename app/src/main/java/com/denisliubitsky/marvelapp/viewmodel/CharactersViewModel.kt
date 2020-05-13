package com.denisliubitsky.marvelapp.viewmodel

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.denisliubitsky.marvelapp.data.repository.RetrofitMarvelRepository
import com.denisliubitsky.marvelapp.model.domain.MarvelCharacter
import com.denisliubitsky.marvelapp.network.NetworkState

class CharactersViewModel(): ViewModel() {
    private var listing: Listing<MarvelCharacter>
    private var refreshState: LiveData<NetworkState>
    private var networkState: LiveData<NetworkState>

    private var retrofitMarvelRepository: RetrofitMarvelRepository = RetrofitMarvelRepository()
    private var dataSourceFactory = retrofitMarvelRepository.getDataSourceFactory()
    private var marvelCharacters: LiveData<PagedList<MarvelCharacter>>

    private val _navigateToSelectedCharacter = MutableLiveData<MarvelCharacter>()
    val navigateToSelectedCharacter: LiveData<MarvelCharacter>
        get() = _navigateToSelectedCharacter

    init {
        val config = pagedListConfig()
        val livePagedListBuilder = LivePagedListBuilder(dataSourceFactory, config).build()

        listing = Listing(
            pagedList = livePagedListBuilder,
            networkState = Transformations.switchMap(dataSourceFactory.source) {
                it.networkState
            },
            retry = {
                dataSourceFactory.source.value?.retryAllFailed()
            },
            refresh = {
                dataSourceFactory.source.value?.invalidate()
            },
            refreshState = Transformations.switchMap(dataSourceFactory.source) {
                it.initialLoad
            },
            clearCoroutineJobs = {
                dataSourceFactory.source.value?.clearCoroutineJobs()
            }
        )

        marvelCharacters = listing.pagedList
        networkState = listing.networkState
        refreshState = listing.refreshState
    }

    fun retry() {
        listing.retry.invoke()
    }

    fun charactersList() = marvelCharacters

    fun currentNetworkState(): LiveData<NetworkState> = networkState

    fun initialNetworkState(): LiveData<NetworkState> = refreshState


    fun displayPropertyDetails(marvelCharacter: MarvelCharacter) {
        _navigateToSelectedCharacter.value = marvelCharacter
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedCharacter.value = null
    }

    fun updateList() {
        listing.refresh.invoke()
    }

    private fun pagedListConfig() = PagedList.Config.Builder()
        .setInitialLoadSizeHint(50)
        .setPageSize(50)
        .setEnablePlaceholders(false)
        .build()

    override fun onCleared() {
        super.onCleared()
        listing.clearCoroutineJobs.invoke()
    }
}