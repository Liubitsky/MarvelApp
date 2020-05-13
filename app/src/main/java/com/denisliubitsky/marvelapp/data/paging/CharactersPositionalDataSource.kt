package com.denisliubitsky.marvelapp.data.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource
import com.denisliubitsky.marvelapp.model.domain.MarvelCharacter
import com.denisliubitsky.marvelapp.model.network.DataWrapper
import com.denisliubitsky.marvelapp.model.network.MarvelWebService
import com.denisliubitsky.marvelapp.network.ApiClient
import com.denisliubitsky.marvelapp.network.NetworkState
import kotlinx.coroutines.*
import retrofit2.Response

class CharactersPositionalDataSource(private val webService: MarvelWebService): PositionalDataSource<MarvelCharacter>() {
    private val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)
    private var retry: (() -> Any)? = null
    val networkState = MutableLiveData<NetworkState>()
    val initialLoad = MutableLiveData<NetworkState>()

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<MarvelCharacter>) {
        coroutineScope.launch {
            try {
                initialLoad.postValue(NetworkState.LOADING)
                val response = callGetCharactersAsync(params.requestedStartPosition, params.requestedLoadSize)
                if(response.isSuccessful) {
                    initialLoad.postValue(NetworkState.LOADED)
                    callback.onResult(response.body()?.data?.results ?: listOf(), params.requestedStartPosition)
                } else {
                    retry = {
                        loadInitial(params, callback)
                    }
                    initialLoad.postValue(NetworkState.error("Network error"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                retry = {
                    loadInitial(params, callback)
                }
                val error = NetworkState.error("Network error")
                initialLoad.postValue(error)
            }
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MarvelCharacter>) {
        coroutineScope.launch {
            try {
                networkState.postValue(NetworkState.LOADING)
                val response = callGetCharactersAsync(params.startPosition, params.loadSize)
                if(response.isSuccessful) {
                    networkState.postValue(NetworkState.LOADED)
                    retry = null
                    callback.onResult(response.body()?.data?.results ?: listOf())
                } else {
                    retry = {
                        loadRange(params, callback)
                    }
                    networkState.postValue(NetworkState.error("Network error"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                retry = {
                    loadRange(params, callback)
                }
                networkState.postValue(NetworkState.error("Network error"))
            }
        }
    }

    private suspend fun callGetCharactersAsync(offset: Int, size: Int): Response<DataWrapper<List<MarvelCharacter>>> =
        webService.getCharactersAsync(offset, size)

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.invoke()
    }

    fun clearCoroutineJobs() {
        completableJob.cancel()
    }
}