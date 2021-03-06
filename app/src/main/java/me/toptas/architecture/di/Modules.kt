package me.toptas.architecture.di

import me.toptas.architecture.data.repository.repositoryModule
import me.toptas.architecture.network.networkModule

val modules = listOf(
    appModule,
    networkModule,
    repositoryModule,
    viewModelModule
)