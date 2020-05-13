package com.denisliubitsky.marvelapp.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.denisliubitsky.marvelapp.R
import com.denisliubitsky.marvelapp.model.domain.CharacterDetails
import com.denisliubitsky.marvelapp.model.domain.Item
import com.denisliubitsky.marvelapp.model.domain.MarvelCharacter
import com.denisliubitsky.marvelapp.network.NetworkState
import com.denisliubitsky.marvelapp.network.Status
import com.denisliubitsky.marvelapp.view.adapter.CharacterDetailsAdapter
import com.denisliubitsky.marvelapp.view.adapter.CharactersAdapter

@BindingAdapter("imageUrl")
fun ImageView.bindImage(imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()

        Glide.with(context)
            .load(imgUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(this)
    }
}

@BindingAdapter("listCharacters")
fun RecyclerView.bindCharactersRecyclerView(data: PagedList<MarvelCharacter>?) {
    val adapter = this.adapter as CharactersAdapter
    adapter.submitList(data)
}

@BindingAdapter("listDetails")
fun RecyclerView.bindDetailsRecyclerView(data: List<CharacterDetails>?) {
    if(!data.isNullOrEmpty()) this.adapter = CharacterDetailsAdapter(data)
}

@BindingAdapter("truncatedStr", "truncatedStrLength")
fun TextView.bindTruncatedString(str: String, length: Int) {
    text = if (str.isNotEmpty()) str.smartTruncate(length)
    else context.getString(R.string.no_description)
}

@BindingAdapter( "initialNetworkState")
fun SwipeRefreshLayout.isRefreshing(initialNetworkState: LiveData<NetworkState>) {
    isRefreshing = initialNetworkState.value?.status == Status.RUNNING
    if(initialNetworkState.value?.status == Status.FAILED) isRefreshing = false
}

@BindingAdapter("completeString")
fun TextView.completeString(strings: List<String>) {
    text = if(strings.isNullOrEmpty()) context.getString(R.string.no_data)
    else {
        strings.joinToString(transform = { " \u2022 $it" }, separator = "\n")
    }
}

@BindingAdapter("descriptionText")
fun TextView.bindDescription(description: String) {
    text = if(description.isNotEmpty()) {
        description
    } else context.getString(R.string.no_description)
}