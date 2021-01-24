package me.toptas.architecture.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.toptas.architecture.ext.postFalse
import me.toptas.architecture.ext.postTrue
import me.toptas.architecture.model.AError
import me.toptas.architecture.model.ApiResponse

open class BaseViewModel : ViewModel() {

    val loadingLive = SingleLiveEvent<Boolean>()
    val baseErrorLive = SingleLiveEvent<AError>()
    val finishLive = SingleLiveEvent<Boolean>()
    val toastLive = SingleLiveEvent<String>()

    fun showLoading() = loadingLive.postTrue()
    fun hideLoading() = loadingLive.postFalse()
    fun postError(err: AError) = baseErrorLive.postValue(err)

    fun <T> callService(
        service: suspend () -> ApiResponse<T>,
        success: (response: T) -> Unit,
        failure: (err: AError) -> Unit = { err -> postError(err) },
        showLoading: Boolean = true
    ) {
        if (showLoading) showLoading()
        viewModelScope.launch {
            val response = service()
            hideLoading()
            if (response.success != null) {
                success(response.success)
            } else {
                if (response.error != null) {
                    failure(response.error)
                } else {
                    failure(AError.Generic(AError.GENERIC_ERROR_NOT_PARSED))
                }
            }
        }
    }

}