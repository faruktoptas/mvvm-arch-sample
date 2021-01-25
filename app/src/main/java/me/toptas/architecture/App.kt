package me.toptas.architecture

import android.app.Application
import me.toptas.architecture.common.pref.PreferenceWrapper
import me.toptas.architecture.di.modules
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin

class App : Application() {

    private val pref: PreferenceWrapper by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin(this, modules)
        pref.init(this)
    }
}