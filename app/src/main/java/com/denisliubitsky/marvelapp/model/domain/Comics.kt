package com.denisliubitsky.marvelapp.model.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
): Parcelable