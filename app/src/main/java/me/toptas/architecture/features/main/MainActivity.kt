package me.toptas.architecture.features.main

import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import me.toptas.architecture.R
import me.toptas.architecture.base.BaseActivity
import me.toptas.architecture.common.ext.observeNotNull
import me.toptas.architecture.ext.asAdapterItems
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViewModel(viewModel)

        viewModel.fetchItems()
        val adapter = AlbumAdapter()
        rv.adapter = adapter
        viewModel.albumsLive.asAdapterItems(this, adapter)

    }

}