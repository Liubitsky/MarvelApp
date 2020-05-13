package com.denisliubitsky.marvelapp.network

import com.denisliubitsky.marvelapp.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE

fun makeLoggingInterceptor() = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG) BODY else NONE
}