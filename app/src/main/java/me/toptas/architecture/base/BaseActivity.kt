package me.toptas.architecture.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import me.toptas.architecture.common.ext.observeIfTrue
import me.toptas.architecture.common.ext.observeNotNull
import me.toptas.architecture.common.model.AError
import me.toptas.architecture.view.LoadingDialog

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var loadingIndicator: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingIndicator = LoadingDialog(this)
    }

    fun bindViewModel(vm: BaseViewModel) {
        vm.loadingLive.observeNotNull(this) {
            if (isFinishing) return@observeNotNull
            if (it) {
                loadingIndicator.show()
            } else {
                loadingIndicator.hide()
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
        val msg = when (error) {
            AError.Network -> "Network error"
            is AError.Business -> error.msg
            else -> "Generic error"
        }
        showAlert(msg)
    }

    private fun showAlert(message: String) {
        val builder = MaterialAlertDialogBuilder(this).apply {
            setTitle("Alert")
            setMessage(message)
            setCancelable(false)
            setPositiveButton("Ok") { _, _ -> }
        }
        builder.show()

    }


}