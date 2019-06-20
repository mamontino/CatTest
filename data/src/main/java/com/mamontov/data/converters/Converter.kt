package com.mamontov.data.converters

import javax.inject.Inject

interface Converter<From, To> {

	fun convert(from: From): To

}

interface TwoSideConverter<From, To> : Converter<From, To> {

	fun revert(to: To): From

}

class ListConverter<From, To> @Inject constructor(private val itemConverter: Converter<From, To>)
	: Converter<List<@JvmSuppressWildcards From>, List<@JvmSuppressWildcards To>> {

	override fun convert(from: List<From>) = from.map { itemConverter.convert(it) }

}