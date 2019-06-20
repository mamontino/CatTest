package com.mamontov.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.OkHttpClient

fun OkHttpClient.Builder.insertInterceptors(
	headerInterceptor: Interceptor,
	errorInterceptor: Interceptor,
	networkInterceptor: Interceptor?
): OkHttpClient.Builder {
	addInterceptor(headerInterceptor)
	addInterceptor(errorInterceptor)
	networkInterceptor?.let(::addNetworkInterceptor)
	return this
}