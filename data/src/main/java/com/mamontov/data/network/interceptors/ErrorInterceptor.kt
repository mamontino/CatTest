//package com.mamontov.data.network.interceptors
//
//import com.google.gson.Gson
//import okhttp3.Interceptor
//import okhttp3.Response
//import ru.ftc.qpay.camp.domain.EMPTY_STRING
//import ru.ftc.qpay.data.model.ErrorModel
//import ru.ftc.qpay.domain.DomainException
//import ru.ftc.qpay.domain.NetworkException
//import ru.ftc.qpay.domain.NoContentException
//import ru.ftc.qpay.domain.ServiceTimeoutException
//import ru.ftc.qpay.domain.entity.ErrorType
//import java.io.IOException
//import java.net.*
//
//abstract class ErrorInterceptor(private val gson: Gson,
//								private val exceptionFactory: ExceptionFactory
//) : Interceptor {
//
//	companion object {
//		private const val SESSION_EXPIRED_MESSAGE = "Сессия пользователя завершена"
//		private const val BAD_PARAMS_MESSAGE = "Некорректные значения параметров"
//		private const val INNER_ERROR_MESSAGE = "Внутренняя ошибка"
//		private const val NOT_FOUND_MESSAGE = "Операция не может быть выполнена"
//		private const val SERVICE_UNAVAILABLE_MESSAGE = "Сервис недоступен"
//		private const val NO_INTERNET_CONNECTION = "Нет подключения к Интернету"
//		private const val NO_CONTENT = "Контент отсутствует"
//	}
//
//	override fun intercept(chain: Interceptor.Chain): Response {
//
//		try {
//			val request = chain.request()
//			val response = chain.proceed(request)
//
//			if (response.code() == HttpURLConnection.HTTP_OK) {
//				return response
//			} else {
//				throw when (response.code()) {
//					HttpURLConnection.HTTP_BAD_REQUEST     -> parseErrorResponse(response)
//					HttpURLConnection.HTTP_UNAUTHORIZED    -> createSessionExpiredException()
//					HttpURLConnection.HTTP_NOT_FOUND       -> createNotFoundException()
//					HttpURLConnection.HTTP_BAD_METHOD      -> createNotFoundException()
//					HttpURLConnection.HTTP_NOT_ACCEPTABLE  -> createInnerException()
//					HttpURLConnection.HTTP_CONFLICT        -> createInnerException()
//					HttpURLConnection.HTTP_INTERNAL_ERROR  -> createInnerException()
//					HttpURLConnection.HTTP_UNAVAILABLE     -> createServiceUnavailableException()
//					HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> createServiceTimeOutException()
//					HttpURLConnection.HTTP_NO_CONTENT      -> createNoContentException()
//					else                                   -> createInnerException()
//				}
//			}
//		} catch (e: IOException) {
//			throw parseException(e)
//		}
//
//	}
//
//	private fun parseErrorResponse(response: Response): RuntimeException {
//		return try {
//			val bodyString: String = readToText(response)
//
//			val errorModel = gson.fromJson(bodyString, ErrorModel::class.java)
//
//			return if (errorModel?.code != null && errorModel.message != null) {
//				onErrorFromResponseBody(errorModel.code, errorModel.message)
//
//			} else {
//				createInnerException()
//			}
//		} catch (e: Exception) {
//			createBadParamsException()
//		}
//	}
//
//	private fun parseException(exception: IOException): RuntimeException =
//		when (exception) {
//			is UnknownHostException,
//			is ConnectException,
//			is NoRouteToHostException -> createNetworkException(exception)
//
//			is SocketTimeoutException -> createServiceTimeOutException(exception)
//
//			else                      -> createServiceUnavailableException()
//		}
//
//	protected abstract fun onErrorFromResponseBody(code: Int, message: String): RuntimeException
//
//	private fun createServiceUnavailableException(): DomainException =
//		exceptionFactory.createByCode(ErrorType.SERVICE_UNAVAILABLE.code, SERVICE_UNAVAILABLE_MESSAGE)
//
//	private fun createServiceTimeOutException(exception: IOException? = null): ServiceTimeoutException =
//		ServiceTimeoutException(SERVICE_UNAVAILABLE_MESSAGE).apply {
//			exception?.let(::addSuppressed)
//		}
//
//	private fun createSessionExpiredException(): DomainException =
//		exceptionFactory.createByCode(ErrorType.SESSION_EXPIRED.code, SESSION_EXPIRED_MESSAGE)
//
//	private fun createInnerException(): DomainException =
//		exceptionFactory.createByCode(ErrorType.INNER_ERROR.code, INNER_ERROR_MESSAGE)
//
//	private fun createBadParamsException(): DomainException =
//		exceptionFactory.createByCode(ErrorType.BAD_PARAMS.code, BAD_PARAMS_MESSAGE)
//
//	private fun createNotFoundException(): DomainException =
//		exceptionFactory.createByCode(ErrorType.NOT_FOUND.code, NOT_FOUND_MESSAGE)
//
//	private fun createNetworkException(exception: IOException): NetworkException =
//		NetworkException(NO_INTERNET_CONNECTION).apply {
//			addSuppressed(exception)
//		}
//
//	private fun createNoContentException(): NoContentException =
//		NoContentException(NO_CONTENT)
//
//	private fun readToText(response: Response) = response.body()?.charStream().use { it?.readText() ?: EMPTY_STRING }
//}