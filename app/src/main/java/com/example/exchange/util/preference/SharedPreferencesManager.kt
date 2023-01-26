package com.example.exchange.util.preference


interface SharedPreferencesManager{

    fun setTheme(themeMode: Int)
    fun getSavedTheme(): String?
}