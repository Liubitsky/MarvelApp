package com.denisliubitsky.marvelapp.network

import com.denisliubitsky.marvelapp.BuildConfig
import okhttp3.Interceptor

fun makeQueryInterceptor() = Interceptor { chain ->
    val originalRequest = chain.request()
    val timeStamp = System.currentTimeMillis()

    val url = originalRequest.url.newBuilder()
        .addQueryParameter("apikey", BuildConfig.MARVEL_API_PUBLIC_KEY)
        .addQueryParameter(
            "hash",
            "${timeStamp}${BuildConfig.MARVEL_API_PRIVATE_KEY}${BuildConfig.MARVEL_API_PUBLIC_KEY}".md5()
        )
        .addQueryParameter("ts", "$timeStamp")
        .build()

    val request = originalRequest
        .newBuilder()
        .url(url)
        .build()
    chain.proceed(request)
}