package com.example.exchange.util.preference

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.example.exchange.util.Constants.Companion.APP_THEME_PREFERENCE
import com.example.exchange.util.Constants.Companion.THEME_UNDEFINED
import javax.inject.Inject

class SharedPreferencesManagerImpl @Inject constructor(private val context: Context) : SharedPreferencesManager{


    private val preferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(context)
    }

    override fun setTheme(themeMode: Int) = AppCompatDelegate.setDefaultNightMode(themeMode)

    override fun getSavedTheme() = preferences.getString(APP_THEME_PREFERENCE, THEME_UNDEFINED)
}