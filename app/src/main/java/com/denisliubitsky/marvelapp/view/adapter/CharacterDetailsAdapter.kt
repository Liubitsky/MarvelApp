package com.denisliubitsky.marvelapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.denisliubitsky.marvelapp.databinding.CharacterDetailsItemBinding
import com.denisliubitsky.marvelapp.model.domain.CharacterDetails

class CharacterDetailsAdapter(private val detailsList: List<CharacterDetails>) :
    RecyclerView.Adapter<CharacterDetailsAdapter.DetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        return DetailsViewHolder(
            CharacterDetailsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        val item = detailsList[position]
        holder.bind(item)
    }

    inner class DetailsViewHolder(private var binding: CharacterDetailsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(characterDetails: CharacterDetails) {
            binding.characterDetails = characterDetails
            binding.titleTextView.setOnClickListener {
                characterDetails.isExpanded = !characterDetails.isExpanded
                notifyItemChanged(adapterPosition)
            }
            binding.expandableLayout.visibility =
                if (characterDetails.isExpanded) View.VISIBLE else View.GONE
            binding.executePendingBindings()
        }
    }

    override fun getItemCount() = detailsList.size
}