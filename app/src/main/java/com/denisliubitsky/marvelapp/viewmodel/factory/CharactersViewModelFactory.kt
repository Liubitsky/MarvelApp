package com.denisliubitsky.marvelapp.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.denisliubitsky.marvelapp.data.paging.CharactersPositionalDataSourceFactory
import com.denisliubitsky.marvelapp.viewmodel.CharactersViewModel

class CharactersViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharactersViewModel::class.java)) {
            return CharactersViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}