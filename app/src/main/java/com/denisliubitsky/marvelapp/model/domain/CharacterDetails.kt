package com.denisliubitsky.marvelapp.model.domain

data class CharacterDetails(val title: String, val items: List<String>?) {
    var isExpanded: Boolean = false
}