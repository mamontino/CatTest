@file:Suppress("NOTHING_TO_INLINE")

package com.mamontov.domain

import timber.log.Timber
import java.util.*
import java.util.regex.Pattern
import kotlin.reflect.KClass

fun String?.orNullIfEmpty(): String? {
	return if (isNullOrEmpty()) null else this
}

inline fun logError(message: String) {
	Timber.e(message)
}

inline fun logError(error: Throwable) {
	Timber.e(error)
}

inline fun logInfo(message: String, tag: String? = null) {
	tag?.also {
		Timber.tag(tag)
	}
	Timber.i(message)
}

inline fun logSevere(message: String) {
	Timber.wtf(message)
}

inline fun logDebug(message: String, tag: String? = null) {
	tag?.also {
		Timber.tag(tag)
	}

	Timber.d(message)
}

inline fun logWarning(message: String) {
	Timber.w(message)
}

fun <T : Enum<*>> KClass<T>.find(value: String): T {
	this.java.enumConstants.forEach {
		if (it.toString().replace("_", "").equals(value, true)) {
			return it
		}
	}

	throw IllegalArgumentException("No enum: ${this.java.simpleName} constant $value")
}

fun Any.toCamelCaseString(): String {

	val pattern = Pattern.compile("[a-z0-9]+")

	val string = this.toString().toLowerCase(Locale.ENGLISH)
	val matcher = pattern.matcher(string)

	var result = ""
	var word: String

	while (matcher.find()) {
		word = matcher.group()

		result += when {
			result.isEmpty() -> word
			else             -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase()
		}
	}

	return result
}

