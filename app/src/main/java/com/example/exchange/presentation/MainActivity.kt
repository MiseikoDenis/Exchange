package com.example.exchange.presentation

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.exchange.R
import com.example.exchange.util.Constants.Companion.APP_THEME_PREFERENCE_DARK
import com.example.exchange.util.Constants.Companion.APP_THEME_PREFERENCE_LIGHT
import com.example.exchange.util.Constants.Companion.APP_THEME_PREFERENCE_SYSTEM
import com.example.exchange.util.Constants.Companion.THEME_UNDEFINED
import com.example.exchange.util.preference.SharedPreferencesManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        baseContext.appComponent.inject(this)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        initView()
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.startFragment,
                R.id.currenciesFragment,
                R.id.dynamicFragment,
                R.id.aboutFragment,
                R.id.settings
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun initView() {
        when (sharedPreferencesManager.getSavedTheme()) {
            APP_THEME_PREFERENCE_LIGHT -> setDefaultNightMode(MODE_NIGHT_NO)
            APP_THEME_PREFERENCE_DARK -> setDefaultNightMode(MODE_NIGHT_YES)
            APP_THEME_PREFERENCE_SYSTEM -> setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
            THEME_UNDEFINED -> setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }


}