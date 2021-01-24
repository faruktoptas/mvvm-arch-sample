package me.toptas.architecture.base

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.toptas.architecture.ext.observeIfTrue
import me.toptas.architecture.ext.observeNotNull
import me.toptas.architecture.model.AError

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun bindViewModel(vm: BaseViewModel) {
        vm.loadingLive.observeNotNull(this) {
            if (isFinishing) return@observeNotNull
            if (it) {
                // show loading
            } else {
                // hide loading
            }
        }

        vm.baseErrorLive.observeNotNull(this, ::showError)

        vm.finishLive.observeIfTrue(this) { finish() }

        vm.toastLive.observeNotNull(this) { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }

    }

    /**
     * All business and networks errors must be handled here
     */
    private fun showError(error: AError) {
        // show error
        Log.v("asd", "$error")
    }


}