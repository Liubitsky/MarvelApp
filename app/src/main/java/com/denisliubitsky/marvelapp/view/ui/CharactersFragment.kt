package com.denisliubitsky.marvelapp.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.denisliubitsky.marvelapp.R
import com.denisliubitsky.marvelapp.databinding.FragmentCharactersBinding
import com.denisliubitsky.marvelapp.view.adapter.CharactersAdapter
import com.denisliubitsky.marvelapp.viewmodel.CharactersViewModel
import com.denisliubitsky.marvelapp.viewmodel.factory.CharactersViewModelFactory

class CharactersFragment: Fragment() {
    private val viewModel: CharactersViewModel by lazy {
        ViewModelProvider(this, CharactersViewModelFactory())
            .get(CharactersViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentCharactersBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.swipeRefreshCharacters.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorPrimary, R.color.colorAccent)
        binding.swipeRefreshCharacters.setProgressViewOffset (false,0, 400)
        binding.swipeRefreshCharacters.setOnRefreshListener {
            viewModel.updateList()
        }

        binding.charactersList.adapter = CharactersAdapter(CharactersAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })

        viewModel.navigateToSelectedCharacter.observe(viewLifecycleOwner, Observer { selectedCharacter ->
            if (selectedCharacter != null) {
                this.findNavController().navigate(
                    CharactersFragmentDirections.actionCharactersFragmentToDetailsFragment(selectedCharacter))
                viewModel.displayPropertyDetailsComplete()
            }
        })

        return binding.root
    }
}