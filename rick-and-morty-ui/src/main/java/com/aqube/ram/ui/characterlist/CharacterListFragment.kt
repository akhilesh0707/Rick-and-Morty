package com.aqube.ram.ui.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aqube.ram.R
import com.aqube.ram.databinding.FragmentCharacterListBinding
import com.aqube.ram.extension.showSnackBar
import com.aqube.ram.presentation.viewmodel.CharacterListViewModel
import com.aqube.ram.presentation.viewmodel.CharacterState
import com.aqube.ram.ui.core.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "CharacterListFragment"

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private val viewModel: CharacterListViewModel by viewModels()
    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var characterAdapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setupRecyclerView()
        characterAdapter.setItemClickListener { character ->
            binding.root.findNavController()
                .navigate(R.id.action_characterListFragment_to_characterDetailFragment)
        }
    }

    private fun setupRecyclerView() = binding.recyclerViewCharacters.apply {
        adapter = characterAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setObservers() {
        viewModel.stateObservable.observe(viewLifecycleOwner) { characterState ->
            updateView(characterState)
        }
        viewModel.getCharacters()
    }

    private fun updateView(characterState: CharacterState) {
        when (characterState) {
            is CharacterState.Success -> {
                characterAdapter.list = characterState.characters
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