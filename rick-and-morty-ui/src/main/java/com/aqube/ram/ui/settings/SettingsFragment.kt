package com.aqube.ram.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aqube.ram.databinding.FragmentSettingsBinding
import com.aqube.ram.domain.models.Settings
import com.aqube.ram.extension.observe
import com.aqube.ram.extension.showSnackBar
import com.aqube.ram.presentation.utils.Resource
import com.aqube.ram.presentation.utils.Resource.Status.*
import com.aqube.ram.presentation.viewmodel.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private val viewModel: SettingsViewModel by viewModels()
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

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
        observe(viewModel.settings, ::onViewStateChange)
        setupRecyclerView()
        viewModel.getSettings()
    }

    private fun onViewStateChange(result: Resource<List<Settings>>) {
        when (result.status) {
            SUCCESS -> {
                result.data?.let {
                    settingsAdapter.list = it
                }
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
        binding.recyclerViewSettings.apply {
            adapter = settingsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        settingsAdapter.setItemClickListener { selectedSetting ->
            viewModel.setSettings(selectedSetting)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}