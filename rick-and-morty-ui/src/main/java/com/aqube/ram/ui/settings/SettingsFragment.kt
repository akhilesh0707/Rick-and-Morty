package com.aqube.ram.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aqube.ram.databinding.FragmentSettingsBinding
import com.aqube.ram.domain.models.Character
import com.aqube.ram.extension.showSnackBar
import com.aqube.ram.presentation.utils.Resource
import com.aqube.ram.presentation.utils.Resource.Status.*
import com.aqube.ram.presentation.viewmodel.CharacterDetailViewModel
import com.aqube.ram.ui.characterlist.CharacterAdapter
import com.aqube.ram.ui.characterlist.CharacterListFragmentDirections
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private val viewModel: CharacterDetailViewModel by viewModels()
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var glide: RequestManager

    @Inject
    lateinit var settingsAdapter: SettingsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // observe(viewModel.character, ::onViewStateChange)
        setupRecyclerView()
    }

    private fun onViewStateChange(result: Resource<Character>) {
        when (result.status) {
            SUCCESS -> {

            }
            ERROR -> {
                val error = result.message ?: "Error"
                Timber.e(error)
                showSnackBar(binding.rootView, error)
            }
            LOADING -> {

            }
        }
    }

    private fun setupRecyclerView() {
        settingsAdapter.list = listOf("Theme mode", "Clear cache", "App version")
        binding.recyclerViewSettings.apply {
            adapter = settingsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        settingsAdapter.setItemClickListener { settingId ->
            setupRecyclerView()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}