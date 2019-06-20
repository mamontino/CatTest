//package com.mamontov.data.network.interceptors
//
//import ru.ftc.qpay.domain.DomainException
//import ru.ftc.qpay.domain.entity.ErrorType
//
//interface ExceptionFactory {
//
//	fun createByCode(code: Int, message: String): DomainException
//}
//
//class ExceptionFactoryImpl : ExceptionFactory {
//
//	override fun createByCode(code: Int, message: String): DomainException {
//		return DomainException(message, findErrorType(code))
//	}
//
//	private fun findErrorType(code: Int): ErrorType = ErrorType.values().firstOrNull { code == it.code } ?: ErrorType.UNKNOWN
//
//}