package com.example.exchange.presentation

import android.app.Application
import android.content.Context
import com.example.exchange.AppComponent
import com.example.exchange.DaggerAppComponent
import com.example.exchange.util.di.RepositoryModule
import com.example.exchange.util.di.RoomModule

class ExchangeApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent =
            DaggerAppComponent.builder()
                .roomModule(RoomModule(applicationContext))
                .build()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is ExchangeApp -> appComponent
        else -> this.applicationContext.appComponent
    }