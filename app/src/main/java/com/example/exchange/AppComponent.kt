package com.example.exchange

import android.content.Context
import com.example.exchange.presentation.currencies.CurrenciesViewModel
import com.example.exchange.presentation.dynamic.DynamicViewModel
import com.example.exchange.presentation.settings.SettingsFragment
import com.example.exchange.presentation.start.StartViewModel
import com.example.exchange.repository.CurrenciesRepository
import com.example.exchange.util.di.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface AppComponent {

    fun inject(target: CurrenciesRepository)
    fun inject(target: CurrenciesViewModel)
    fun inject(target: StartViewModel)
    fun inject(target: SettingsFragment)
    fun inject(target: DynamicViewModel)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun buildContext(context: Context): Builder
        fun build(): AppComponent
    }
}