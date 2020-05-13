package com.denisliubitsky.marvelapp.model.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnail(
    val path: String,
    val extension: String
): Parcelable {
    val completePathDefault: String
        get() = "$path.$extension"
    val completePathStandard: String
        get() = "$path/${ThumbnailFormat.STANDARD.type}.$extension"
    val completePathPortrait: String
        get() = "$path/${ThumbnailFormat.PORTRAIT.type}.$extension"
    val completePathLandscape: String
        get() = "$path/${ThumbnailFormat.LANDSCAPE.type}.$extension"
}

enum class ThumbnailFormat(val type: String) {
    PORTRAIT("portrait_uncanny"),
    STANDARD("standard_fantastic"),
    LANDSCAPE("landscape_incredible")
}