package com.mamontov.data.network

import com.google.gson.Gson
import com.mamontov.data.models.ErrorResponse
import retrofit2.Response
import javax.inject.Inject

class ErrorHandler @Inject constructor(private val gson: Gson) {

    fun parseError(response: Response<*>): ErrorResponse {

        return try {
            return gson.fromJson<ErrorResponse>(response.errorBody()?.string(), ErrorResponse::class.java)
        } catch (e: Exception) {
            ErrorResponse(message = e.message)
        } finally {
            response.errorBody()?.close()
        }
    }
}