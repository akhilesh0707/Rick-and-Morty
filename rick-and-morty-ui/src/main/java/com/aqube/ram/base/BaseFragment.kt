package com.aqube.ram.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import com.aqube.ram.presentation.viewmodel.BaseViewModel
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.aqube.ram.core.dialog.dismissLoadingDialog
import com.aqube.ram.core.dialog.showLoadingDialog
import com.aqube.ram.extension.showSnackBar
import timber.log.Timber

abstract class BaseFragment<ViewBinding : ViewDataBinding, ViewModel : BaseViewModel> : Fragment() {

    protected lateinit var viewBinding: ViewBinding
    protected abstract val viewModel: ViewModel

    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerEvents()
    }

    private fun observerEvents() {
        viewModel.apply {
            /*isLoading.observe(viewLifecycleOwner, {
                handleLoading(it == true)
            })
            errorMessage.observe(viewLifecycleOwner, {
                handleErrorMessage(it)
            })
            noInternetConnectionEvent.observe(viewLifecycleOwner, {
                handleErrorMessage(getString(R.string.no_internet_connection))
            })
            connectTimeoutEvent.observe(viewLifecycleOwner, {
                handleErrorMessage(getString(R.string.connect_timeout))
            })
            forceUpdateAppEvent.observe(viewLifecycleOwner, {
                handleErrorMessage(getString(R.string.force_update_app))
            })
            serverMaintainEvent.observe(viewLifecycleOwner, {
                handleErrorMessage(getString(R.string.server_maintain_message))
            })
            unknownErrorEvent.observe(viewLifecycleOwner, {
                handleErrorMessage(getString(R.string.unknown_error))
            })*/
        }
    }


    protected open fun handleLoading(isLoading: Boolean) {
        if (isLoading) showLoadingDialog() else dismissLoadingDialog()
    }

    protected open fun handleErrorMessage(message: String?) {
        if (message.isNullOrBlank()) return
        dismissLoadingDialog()
        Timber.e(message)
        showSnackBar(viewBinding.root, message)
    }

}