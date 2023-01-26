package com.example.exchange.presentation.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.Preference.OnPreferenceChangeListener
import androidx.preference.PreferenceFragmentCompat
import com.example.exchange.R
import com.example.exchange.presentation.appComponent
import com.example.exchange.util.Constants.Companion.APP_THEME_PREFERENCE
import com.example.exchange.util.Constants.Companion.APP_THEME_PREFERENCE_DARK
import com.example.exchange.util.Constants.Companion.APP_THEME_PREFERENCE_LIGHT
import com.example.exchange.util.Constants.Companion.APP_THEME_PREFERENCE_SYSTEM
import com.example.exchange.util.preference.SharedPreferencesManager
import javax.inject.Inject

class Settings : PreferenceFragmentCompat() {

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        context?.appComponent?.inject(this)

        val themePreference: ListPreference? = findPreference(APP_THEME_PREFERENCE)
        themePreference?.apply {
            entries = arrayOf(
                getString(R.string.system),
                getString(R.string.light),
                getString(R.string.dark)
            )
            entryValues = arrayOf(
                APP_THEME_PREFERENCE_SYSTEM,
                APP_THEME_PREFERENCE_LIGHT,
                APP_THEME_PREFERENCE_DARK
            )
            setDefaultValue(APP_THEME_PREFERENCE_SYSTEM)
        }

        themePreference?.onPreferenceChangeListener =
            OnPreferenceChangeListener { _, newValue ->
                when (newValue) {
                    APP_THEME_PREFERENCE_DARK -> sharedPreferencesManager.setTheme(
                        AppCompatDelegate.MODE_NIGHT_YES
                    )
                    APP_THEME_PREFERENCE_LIGHT -> sharedPreferencesManager.setTheme(
                        AppCompatDelegate.MODE_NIGHT_NO
                    )
                    APP_THEME_PREFERENCE_SYSTEM -> sharedPreferencesManager.setTheme(
                        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    )
                }
                true
            }
    }

}