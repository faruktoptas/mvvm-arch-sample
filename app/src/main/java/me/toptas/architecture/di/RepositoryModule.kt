package me.toptas.architecture.di

import me.toptas.architecture.features.main.MainRepository
import me.toptas.architecture.features.main.MainRepositoryImpl
import org.koin.dsl.module.module

val repositoryModule = module {

    single<MainRepository> { MainRepositoryImpl(get()) }

}