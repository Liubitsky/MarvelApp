package com.denisliubitsky.marvelapp.model.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    val resourceURI: String,
    val name: String
): Parcelable