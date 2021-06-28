package com.aqube.ram.presentation.utils

import androidx.annotation.StringRes

internal object ExceptionHandler {

    @StringRes
    fun parse(t: Throwable): Int {
        return 0 /*when (t) {
            is UnknownHostException -> R.string.error_check_internet_connection
            else -> R.string.error_oops_error_occured
        }*/
    }
}
