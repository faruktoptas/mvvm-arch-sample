package me.toptas.architecture.common.model

data class ApiResponse<T>(val success: T? = null, val error: AError? = null)