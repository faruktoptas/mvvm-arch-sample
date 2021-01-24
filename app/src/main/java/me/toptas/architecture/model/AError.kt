package me.toptas.architecture.model

sealed class AError {
    object Network : AError()
    object Timeout : AError()
    class SSLError(val code: Int = CODE_SSL) : AError()
    class Generic(val code: Int) : AError()
    class Business(val msg: String) : AError()
    class Authorization(val msg: String? = null) : AError()

    companion object {
        const val GENERIC_ERROR_NOT_PARSED = -1
        const val CODE_CONNECTION = -2
        const val CODE_SSL = -3
    }
}