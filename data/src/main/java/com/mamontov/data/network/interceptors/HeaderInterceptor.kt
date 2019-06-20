package com.mamontov.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor() : Interceptor {

	private companion object {
		private const val AUTH_TOKEN = "x-api-key"
	}

	override fun intercept(chain: Interceptor.Chain): Response {
		val request = chain.request()
		val builder = request.newBuilder()

		builder?.let {
			addSessionHeader(it)
		}

		val changedRequest = builder.build()
		return chain.proceed(changedRequest)
	}

	private fun addSessionHeader(headerBuilder: Request.Builder) {
		try {
				headerBuilder.header(AUTH_TOKEN, "92ccb8a7-8961-41bb-9e77-eb77c92339ff")
		} catch (exception: Throwable) {
			//ignore
		}
	}
}