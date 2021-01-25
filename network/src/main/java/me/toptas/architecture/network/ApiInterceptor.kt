package me.toptas.architecture.network

import me.toptas.architecture.common.pref.PreferenceWrapper
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor(private val pref: PreferenceWrapper) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest = request.newBuilder()
            .header("Authorization", "Bearer ${pref.token}")
            .build()

        return chain.proceed(newRequest)
    }

}