package com.denisliubitsky.marvelapp.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.denisliubitsky.marvelapp.model.domain.MarvelCharacter
import com.denisliubitsky.marvelapp.viewmodel.CharacterDetailsViewModel

class CharacterDetailsViewModelFactory(private val marvelCharacter: MarvelCharacter): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CharacterDetailsViewModel::class.java)) {
            return CharacterDetailsViewModel(marvelCharacter) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}