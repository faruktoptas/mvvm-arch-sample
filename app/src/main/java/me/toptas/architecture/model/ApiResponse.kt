package me.toptas.architecture.model

data class ApiResponse<T>(val success: T? = null, val error: AError? = null)