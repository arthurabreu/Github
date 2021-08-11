package com.arthur.github.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

suspend fun <T> safeApiCall(
    context: CoroutineContext = Dispatchers.IO,
    call: suspend () -> T
): ResultOf<T> = withContext(context) {
    try {
        ResultOf.Success(call.invoke())
    } catch (exception: Exception) {
        ResultOf.Error(exception, exception.message)
    }
}

sealed class ResultOf<out R> {
    data class Success<out T>(val data: T) : ResultOf<T>()
    data class Error(val exception: Exception, val message: String? = null) : ResultOf<Nothing>()
}

val ResultOf<*>.succeeded
    get() = this is ResultOf.Success<*> && data != null

inline fun <T> ResultOf<T>.onSuccess(
    crossinline callback: (value: T) -> Unit
): ResultOf<T> {
    if (this is ResultOf.Success<*>) {
        callback((this as ResultOf.Success<T>).data)
    }
    return this
}

inline fun <T> ResultOf<T>.onError(
    crossinline callback: (value: ResultOf.Error) -> Unit
): ResultOf<T> {
    if (this !is ResultOf.Success<*>) {
        callback(this as ResultOf.Error)
    }
    return this
}
