package com.denisliubitsky.marvelapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.denisliubitsky.marvelapp.model.domain.CharacterDetails
import com.denisliubitsky.marvelapp.model.domain.Item
import com.denisliubitsky.marvelapp.model.domain.MarvelCharacter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class CharacterDetailsViewModel(marvelCharacter: MarvelCharacter): ViewModel() {
    private val _character = MutableLiveData<MarvelCharacter>()
    val character: LiveData<MarvelCharacter>
        get() = _character

    val detailsCharacter = Transformations.map(character) {
        val list = ArrayList<CharacterDetails>()
        list.add(mapping("Comics", character.value?.comics?.items))
        list.add(mapping("Series", character.value?.series?.items))
        list.add(mapping("Stories", character.value?.stories?.items))
        list.add(mapping("Events", character.value?.events?.items))
        list.toList()
    }

    private fun mapping(title: String, items: List<Item>?): CharacterDetails {
        return CharacterDetails(title, items?.map{ it.name })
    }

    init {
        _character.value = marvelCharacter
    }
}