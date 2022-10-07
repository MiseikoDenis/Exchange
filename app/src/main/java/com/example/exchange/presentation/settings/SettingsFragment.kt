package com.example.exchange.presentation.settings

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.exchange.R
import com.example.exchange.databinding.FragmentCurrenciesBinding
import com.example.exchange.databinding.FragmentSettingsBinding
import com.example.exchange.databinding.FragmentStartBinding
import com.example.exchange.presentation.ExchangeApp
import com.example.exchange.presentation.appComponent
import com.example.exchange.util.Constants.Companion.APP_THEME_PREFERENCE
import com.example.exchange.util.Constants.Companion.APP_THEME_PREFERENCE_DARK
import com.example.exchange.util.Constants.Companion.APP_THEME_PREFERENCE_LIGHT
import com.example.exchange.util.Constants.Companion.APP_THEME_PREFERENCE_SYSTEM
import com.example.exchange.util.Constants.Companion.THEME_UNDEFINED
import com.example.exchange.util.SharedPreferencesManager
import javax.inject.Inject

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        context?.appComponent?.inject(this)

        initView()
        initTheme()

        return binding.root
    }

    fun initView() {

        binding.themeSettings.setOnCheckedChangeListener { view, checked ->
            when(checked) {
                R.id.dark_theme_radiobutton -> sharedPreferencesManager.setTheme(AppCompatDelegate.MODE_NIGHT_YES, APP_THEME_PREFERENCE_DARK)
                R.id.light_theme_radiobutton -> sharedPreferencesManager.setTheme(AppCompatDelegate.MODE_NIGHT_NO, APP_THEME_PREFERENCE_LIGHT)
                R.id.system_theme_radiobutton -> sharedPreferencesManager.setTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM, APP_THEME_PREFERENCE_SYSTEM)
            }


        }
    }

    private fun initTheme() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            binding.systemThemeRadiobutton.visibility = View.VISIBLE
        } else {
            binding.systemThemeRadiobutton.visibility = View.GONE
        }
        when (sharedPreferencesManager.getSavedTheme()) {
            APP_THEME_PREFERENCE_LIGHT -> binding.lightThemeRadiobutton.isChecked = true
            APP_THEME_PREFERENCE_DARK -> binding.darkThemeRadiobutton.isChecked = true
            APP_THEME_PREFERENCE_SYSTEM -> binding.systemThemeRadiobutton.isChecked = true
            THEME_UNDEFINED -> {
                when (resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)) {
                    Configuration.UI_MODE_NIGHT_NO -> binding.lightThemeRadiobutton.isChecked = true
                    Configuration.UI_MODE_NIGHT_YES -> binding.darkThemeRadiobutton.isChecked = true
                    Configuration.UI_MODE_NIGHT_UNDEFINED -> binding.systemThemeRadiobutton.isChecked = true
                }
            }
        }
    }




}
