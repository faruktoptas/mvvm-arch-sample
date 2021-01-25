package me.toptas.architecture.data

import me.toptas.architecture.data.MainRepository
import me.toptas.architecture.data.MainRepositoryImpl
import org.koin.dsl.module.module

val repositoryModule = module {

    single<MainRepository> { MainRepositoryImpl(get()) }

}