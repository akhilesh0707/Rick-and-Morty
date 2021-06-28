package com.aqube.ram.presentation.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class UiAwareLiveData<T : UiAwareModel> : MutableLiveData<T>() {

    private var previousValue: T? = null

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(
            owner,
            { value: T? ->
                value?.isRedelivered = false
                val inProperState =
                    owner.lifecycle.currentState == Lifecycle.State.CREATED || owner.lifecycle.currentState == Lifecycle.State.STARTED
                if (previousValue == value && inProperState) {
                    value?.isRedelivered = true
                }

                observer.onChanged(value)
                previousValue = value
            }
        )
    }
}
