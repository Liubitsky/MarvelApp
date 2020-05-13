package com.denisliubitsky.marvelapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.denisliubitsky.marvelapp.network.NetworkState

data class Listing<T>(
    val pagedList: LiveData<PagedList<T>>,
    val networkState: LiveData<NetworkState>,
    val refreshState: LiveData<NetworkState>,
    val refresh: () -> Unit,
    val retry: () -> Unit,
    val clearCoroutineJobs: () -> Unit
)