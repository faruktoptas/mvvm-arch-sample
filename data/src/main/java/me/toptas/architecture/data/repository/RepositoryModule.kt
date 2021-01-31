package me.toptas.architecture.data.repository

import org.koin.dsl.module.module

val repositoryModule = module {

    single<MainRepository> { MainRepositoryImpl(get()) }

}