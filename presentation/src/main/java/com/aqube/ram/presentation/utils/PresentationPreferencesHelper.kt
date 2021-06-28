package com.aqube.ram.presentation.utils

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

open class PresentationPreferencesHelper @Inject constructor(context: Context) {

    companion object {
        private const val PREF_PACKAGE_NAME = "com.aqube.ram.presentation.preferences"
        private const val PREF_KEY_NIGHT_MODE = "night_mode"
    }

    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREF_PACKAGE_NAME, Context.MODE_PRIVATE)

    var isNightMode: Boolean
        get() = preferences.getBoolean(PREF_KEY_NIGHT_MODE, false)
        set(isDarkMode) = preferences.edit().putBoolean(PREF_KEY_NIGHT_MODE, isDarkMode).apply()
}
