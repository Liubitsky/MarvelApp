package com.denisliubitsky.marvelapp.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.denisliubitsky.marvelapp.databinding.FragmentDetailsBinding
import com.denisliubitsky.marvelapp.view.adapter.CharacterDetailsAdapter
import com.denisliubitsky.marvelapp.view.adapter.CharactersAdapter
import com.denisliubitsky.marvelapp.viewmodel.CharacterDetailsViewModel
import com.denisliubitsky.marvelapp.viewmodel.factory.CharacterDetailsViewModelFactory

class CharacterDetailsFragment: Fragment() {
    private val viewModel: CharacterDetailsViewModel by lazy {
        val marvelCharacter = CharacterDetailsFragmentArgs.fromBundle(requireArguments()).selectedCharacter
        ViewModelProvider(this, CharacterDetailsViewModelFactory(marvelCharacter)).get(CharacterDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailsBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }
}