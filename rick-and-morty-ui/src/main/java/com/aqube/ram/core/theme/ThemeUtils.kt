package com.aqube.ram.core.theme

import android.content.Context

interface ThemeUtils {
    fun isDarkTheme(context: Context): Boolean
    fun isLightTheme(context: Context): Boolean
    fun setNightMode(nightMode: Boolean)
}
