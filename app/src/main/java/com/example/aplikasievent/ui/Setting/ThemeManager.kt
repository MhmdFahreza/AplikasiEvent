package com.example.aplikasievent.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate

object ThemeManager {
    private const val PREFERENCES_NAME = "theme_preferences"
    private const val THEME_KEY = "theme_key"

    fun applyTheme(themeMode: Int) {
        AppCompatDelegate.setDefaultNightMode(themeMode)
    }

    fun saveThemePreference(context: Context, isDarkMode: Boolean) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean(THEME_KEY, isDarkMode).apply()
    }

    fun isDarkModeEnabled(context: Context): Boolean {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(THEME_KEY, false)
    }
}
