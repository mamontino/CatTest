package com.mamontov.data.models

data class ErrorResponse(
    val code: Int = 0,
    val status: Int = 0,
    val message: String? = null,
    val name: String? = null
)
