package me.toptas.architecture.network

import android.util.Log
import me.toptas.architecture.model.AError
import me.toptas.architecture.model.ApiResponse
import retrofit2.Response
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLException

suspend fun <T> apiWrapper(service: suspend () -> Response<T>): ApiResponse<T> {
    return try {
        val response = service()
        val data = response.body()
        if (response.isSuccessful && data != null) {
            ApiResponse(data)
        } else {
            val httpStatusCode = response.code()
            val errorString = response.errorBody()?.string()
            val error: AError
            error = try {
                val errorMsg = parseErrorMessage(errorString)
                when {
                    httpStatusCode == 401 -> AError.Authorization(errorMsg)
                    !errorMsg.isNullOrEmpty() -> AError.Business(errorMsg)
                    else -> AError.Generic(AError.GENERIC_ERROR_NOT_PARSED)
                }
            } catch (e: Exception) {
                AError.Generic(AError.GENERIC_ERROR_NOT_PARSED)
            }
            return ApiResponse(error = error)
        }
    } catch (e: Exception) {
        Log.v("asd", "$e")
        val error = when (e) {
            is UnknownHostException -> AError.Network
            is TimeoutException -> AError.Timeout
            is SSLException -> AError.SSLError()
            else -> AError.Network
        }
        ApiResponse(error = error)
    }
}

private fun parseErrorMessage(body: String?): String {
    // parse error message from error body
    return "Business error"
}