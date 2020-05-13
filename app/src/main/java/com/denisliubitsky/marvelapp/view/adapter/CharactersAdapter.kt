package com.denisliubitsky.marvelapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.denisliubitsky.marvelapp.databinding.CharactersItemBinding
import com.denisliubitsky.marvelapp.model.domain.MarvelCharacter

class CharactersAdapter(private val onClickListener: OnClickListener) : PagedListAdapter<MarvelCharacter, CharactersAdapter.MarvelCharacterViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharactersAdapter.MarvelCharacterViewHolder {
        return MarvelCharacterViewHolder(
            CharactersItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MarvelCharacterViewHolder, position: Int) {
        val marvelCharacter = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onCLick(marvelCharacter!!)
        }
        holder.bind(marvelCharacter!!)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<MarvelCharacter>() {
        override fun areItemsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: MarvelCharacter,
            newItem: MarvelCharacter
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class MarvelCharacterViewHolder(private var binding: CharactersItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(marvelCharacter: MarvelCharacter) {
            binding.character = marvelCharacter
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (marvelCharacter: MarvelCharacter) -> Unit) {
        fun onCLick(marvelCharacter: MarvelCharacter) = clickListener(marvelCharacter)
    }
}