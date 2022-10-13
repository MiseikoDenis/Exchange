package com.example.exchange.util.preference

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.example.exchange.util.Constants.Companion.APP_PREFERENCE
import com.example.exchange.util.Constants.Companion.APP_THEME_PREFERENCE
import com.example.exchange.util.Constants.Companion.THEME_UNDEFINED
import javax.inject.Inject

class SharedPreferencesManagerImpl @Inject constructor(private val context: Context) : SharedPreferencesManager{


    private val preferences by lazy {
        context.getSharedPreferences(
            APP_PREFERENCE,
            Context.MODE_PRIVATE
        )
    }

    override fun setTheme(themeMode: Int, prefsMode: String) {
        AppCompatDelegate.setDefaultNightMode(themeMode)
        saveTheme(prefsMode)
    }

    override fun saveTheme(theme: String) = preferences.edit().putString(APP_THEME_PREFERENCE, theme).apply()

    override fun getSavedTheme() = preferences.getString(APP_THEME_PREFERENCE, THEME_UNDEFINED)
}