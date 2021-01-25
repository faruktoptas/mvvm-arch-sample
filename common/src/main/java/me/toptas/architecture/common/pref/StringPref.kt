package me.toptas.architecture.common.pref

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class StringPref(private val key: String) :
    ReadWriteProperty<PreferenceWrapper, String> {

    override fun getValue(thisRef: PreferenceWrapper, property: KProperty<*>) =
        thisRef.getString(key).orEmpty()

    override fun setValue(thisRef: PreferenceWrapper, property: KProperty<*>, value: String) {
        thisRef.setString(key, value)
    }

}

fun SharedPreferences.putString(key: String, value: String) {
    edit().putString(key, value).apply()
}