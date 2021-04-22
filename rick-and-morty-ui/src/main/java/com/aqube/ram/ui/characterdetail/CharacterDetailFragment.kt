package com.aqube.ram.ui.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.aqube.ram.databinding.FragmentCharacterDetailBinding
import com.aqube.ram.extension.observe
import com.aqube.ram.extension.showSnackBar
import com.aqube.ram.presentation.viewmodel.CharacterDetailViewModel
import com.aqube.ram.presentation.viewmodel.CharacterState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private val viewModel: CharacterDetailViewModel by viewModels()
    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!
    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showSnackBar(binding.rootView, args.characterId.toString())
        observe(viewModel.stateObservable, ::onViewStateChange)
        viewModel.getCharacterDetail(args.characterId)
    }

    private fun onViewStateChange(characterState: CharacterState) {
        when (characterState) {
            is CharacterState.Success -> {
            }
            is CharacterState.Error -> {
                showSnackBar(binding.rootView, getString(characterState.message), true)
            }
            CharacterState.Init -> {
            }
            CharacterState.Loading -> {
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}