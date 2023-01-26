package com.example.exchange

import android.content.Context
import com.example.exchange.presentation.MainActivity
import com.example.exchange.presentation.currencies.CurrenciesFragment
import com.example.exchange.presentation.currencies.CurrenciesViewModel
import com.example.exchange.presentation.dynamic.DynamicFragment
import com.example.exchange.presentation.dynamic.DynamicViewModel
import com.example.exchange.presentation.settings.Settings
import com.example.exchange.presentation.start.StartFragment
import com.example.exchange.presentation.start.StartViewModel
import com.example.exchange.repository.CurrenciesRepositoryImpl
import com.example.exchange.util.di.BindersModule
import com.example.exchange.util.di.NetworkModule
import com.example.exchange.util.di.RepositoryModule
import com.example.exchange.util.di.RoomModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, BindersModule::class, RoomModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(target: CurrenciesRepositoryImpl)
    fun inject(target: CurrenciesViewModel)
    fun inject(target: StartViewModel)
    fun inject(target: DynamicViewModel)
    fun inject(target: StartFragment)
    fun inject(target: CurrenciesFragment)
    fun inject(target: DynamicFragment)
    fun inject(target: MainActivity)
    fun inject(target: Settings)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun buildContext(context: Context): Builder
        fun build(): AppComponent
    }
}