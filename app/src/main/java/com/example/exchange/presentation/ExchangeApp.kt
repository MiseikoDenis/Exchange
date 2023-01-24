package com.example.exchange.presentation

import android.app.Application
import android.content.Context
import com.example.exchange.AppComponent
import com.example.exchange.DaggerAppComponent

class ExchangeApp : Application() {


    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent =
            DaggerAppComponent.builder()
                .buildContext(this)
                .build()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is ExchangeApp -> appComponent
        else -> this.applicationContext.appComponent
    }