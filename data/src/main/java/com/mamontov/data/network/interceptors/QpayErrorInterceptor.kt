//package com.mamontov.data.network.interceptors
//
//import com.google.gson.Gson
//
//class QpayErrorInterceptor(gson: Gson, private val exceptionFactory: ExceptionFactory) : ErrorInterceptor(gson, exceptionFactory) {
//
//	override fun onErrorFromResponseBody(code: Int, message: String): RuntimeException =
//		exceptionFactory.createByCode(code, message)
//
//}