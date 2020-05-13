package com.denisliubitsky.marvelapp.model.network

class DataContainer<T> (val offset: Int?, val limit: Int?, val total: Int?) {
    var results: T? = null
}