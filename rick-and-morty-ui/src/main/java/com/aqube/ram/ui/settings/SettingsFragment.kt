package com.aqube.ram.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aqube.ram.R
import com.aqube.ram.base.BaseFragment
import com.aqube.ram.core.theme.ThemeUtils
import com.aqube.ram.databinding.FragmentSettingsBinding
import com.aqube.ram.domain.models.Settings
import com.aqube.ram.extension.observe
import com.aqube.ram.presentation.utils.Resource
import com.aqube.ram.presentation.utils.Resource.Status.*
import com.aqube.ram.presentation.viewmodel.BaseViewModel
import com.aqube.ram.presentation.viewmodel.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding, BaseViewModel>() {

    override val layoutId: Int = R.layout.fragment_settings

    override val viewModel: SettingsViewModel by viewModels()

    @Inject
    lateinit var settingsAdapter: SettingsAdapter

    @Inject
    lateinit var themeUtils: ThemeUtils

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.settings, ::onViewStateChange)
        observe(viewModel.nightMode, ::onViewStateChangeNightMode)
        setupRecyclerView()
        viewModel.getSettings()
    }

    private fun onViewStateChange(result: Resource<List<Settings>>) {
        when (result.status) {
            SUCCESS -> {
                handleLoading(false)
                result.data?.let {
                    settingsAdapter.list = it
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

    private fun onViewStateChangeNightMode(result: Resource<Boolean>) {
        when (result.status) {
            SUCCESS -> {
                result.data?.let {
                    themeUtils.setNightMode(it)
                }
            }
            else -> {
            }
        }
    }

    private fun setupRecyclerView() {
        viewBinding.recyclerViewSettings.apply {
            adapter = settingsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        settingsAdapter.setItemClickListener { selectedSetting ->
            viewModel.setSettings(selectedSetting)
        }
    }
}