package com.aqube.ram.ui.characterlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aqube.ram.R
import com.aqube.ram.base.BaseFragment
import com.aqube.ram.databinding.FragmentCharacterListBinding
import com.aqube.ram.domain.models.Character
import com.aqube.ram.extension.observe
import com.aqube.ram.presentation.utils.Resource
import com.aqube.ram.presentation.utils.Resource.Status.*
import com.aqube.ram.presentation.viewmodel.BaseViewModel
import com.aqube.ram.presentation.viewmodel.CharacterListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterListFragment : BaseFragment<FragmentCharacterListBinding, BaseViewModel>() {

    override val layoutId: Int = R.layout.fragment_character_list

    override val viewModel: CharacterListViewModel by viewModels()

    @Inject
    lateinit var characterAdapter: CharacterAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.characterList, ::onViewStateChange)
        viewModel.getCharacters()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        viewBinding.recyclerViewCharacters.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        characterAdapter.setItemClickListener { character ->
            findNavController().navigate(
                CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                    character.id.toLong()
                )
            )
        }
    }

    private fun onViewStateChange(result: Resource<List<Character>>) {
        when (result.status) {
            SUCCESS -> {
                handleLoading(false)
                result.data?.let {
                    characterAdapter.list = it
                }
            }
            ERROR -> {
                val error = result.message ?: "Error"
                handleErrorMessage(error)
            }
            LOADING -> {
                handleLoading(true)
            }
        }
    }
}