package com.example.exchange.util

import android.content.Context
import android.content.res.Resources
import androidx.appcompat.app.AppCompatDelegate
import com.example.exchange.util.Constants.Companion.APP_PREFERENCE
import com.example.exchange.util.Constants.Companion.APP_THEME_PREFERENCE
import com.example.exchange.util.Constants.Companion.APP_THEME_PREFERENCE_DARK
import com.example.exchange.util.Constants.Companion.THEME_UNDEFINED
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(private val context: Context){


    private val preferences by lazy {
        context.getSharedPreferences(
            APP_PREFERENCE,
            Context.MODE_PRIVATE
        )
    }

    fun save(key: String, value: String) {
        preferences.edit()
            .putString(key, value)
            .apply()
    }


    fun setTheme(themeMode: Int, prefsMode: String) {
        AppCompatDelegate.setDefaultNightMode(themeMode)
        saveTheme(prefsMode)
    }



    fun saveTheme(theme: String) = preferences.edit().putString(APP_THEME_PREFERENCE, theme).apply()

    fun getSavedTheme() = preferences.getString(APP_THEME_PREFERENCE, THEME_UNDEFINED)
}