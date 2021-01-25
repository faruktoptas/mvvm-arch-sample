package me.toptas.architecture.di

import me.toptas.architecture.common.pref.PreferenceWrapper
import org.koin.dsl.module.module

val appModule = module {
    single { PreferenceWrapper() }
}