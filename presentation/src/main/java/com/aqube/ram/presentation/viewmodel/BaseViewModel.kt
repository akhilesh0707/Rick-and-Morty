package com.aqube.ram.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


abstract class BaseViewModel : ViewModel() {

    private val job: Job = Job()

    abstract val coroutineExceptionHandler: CoroutineExceptionHandler

    protected fun launchCoroutineIO(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(Dispatchers.IO + job + coroutineExceptionHandler) {
            block()
        }
    }

    protected fun launchCoroutineMain(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(Dispatchers.Main + job + coroutineExceptionHandler) {
            block()
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}