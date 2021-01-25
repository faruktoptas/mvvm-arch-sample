package me.toptas.architecture.common.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class PreferenceWrapper {

    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    var token: String by StringPref(TOKEN)


    fun getString(key: String) = preferences.getString(key, "")

    fun setString(key: String, value: String) = preferences.putString(key, value)

    companion object {
        private const val TOKEN = "token"
    }
}