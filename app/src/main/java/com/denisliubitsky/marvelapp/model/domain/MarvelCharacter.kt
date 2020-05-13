package com.denisliubitsky.marvelapp.model.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarvelCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Thumbnail,
    val resourceURI: String,
    val comics: Comics,
    val series: Series,
    val stories: Stories,
    val events: Events,
    val urls: List<Url>
) : Parcelable