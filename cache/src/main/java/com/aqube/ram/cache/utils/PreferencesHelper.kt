package com.aqube.ram.cache.utils

import android.content.Context
import android.content.SharedPreferences

import javax.inject.Inject
import javax.inject.Singleton

open class PreferencesHelper @Inject constructor(context: Context) {

    companion object {
        private const val PREF_BUFFER_PACKAGE_NAME = "com.aqube.ram.cache.preferences"
        private const val PREF_KEY_LAST_CACHE = "last_cache"
    }

    private val bufferPref: SharedPreferences =
        context.getSharedPreferences(PREF_BUFFER_PACKAGE_NAME, Context.MODE_PRIVATE)

    var lastCacheTime: Long
        get() = bufferPref.getLong(PREF_KEY_LAST_CACHE, 0)
        set(lastCache) = bufferPref.edit().putLong(PREF_KEY_LAST_CACHE, lastCache).apply()

}
