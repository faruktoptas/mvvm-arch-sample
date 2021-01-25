package me.toptas.architecture.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import me.toptas.architecture.R

class LoadingDialog(context: Context): Dialog(context){

    init {
        window?.apply {
            requestFeature(Window.FEATURE_NO_TITLE)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        setContentView(R.layout.dialog_loading)
        setCancelable(false)
    }

}