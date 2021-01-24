package me.toptas.architecture

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import me.toptas.architecture.base.BaseActivity
import me.toptas.architecture.ext.observeNotNull
import me.toptas.architecture.features.main.MainRepositoryImpl
import me.toptas.architecture.features.main.MainViewModel
import me.toptas.architecture.network.NetworkModule

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // todo use inject
        val vm = MainViewModel(MainRepositoryImpl(NetworkModule.api))
        bindViewModel(vm)

        vm.fetchItems()

        vm.albumsLive.observeNotNull(this, { Log.v("asd", "items ${it.size}") })
    }

}