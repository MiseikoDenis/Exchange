package com.example.exchange.util.preference


interface SharedPreferencesManager{

    fun setTheme(themeMode: Int, prefsMode: String)

    fun saveTheme(theme: String)

    fun getSavedTheme(): String?
}