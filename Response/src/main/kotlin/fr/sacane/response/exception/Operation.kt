package fr.sacane.response.exception

import fr.sacane.response.Response
import fr.sacane.response.response


fun <E> ThrowableResponse<E>.and(other: ThrowableResponse<E>): ThrowableResponse<E> = when(this.status) {
    is ExceptionStatus -> throw this.status.ex
    else -> when(other.status) {
        is ExceptionStatus -> throw other.status.ex
        else -> other
    }
}

fun <E> ThrowableResponse<E>.or(other: ThrowableResponse<E>): ThrowableResponse<E> = when(this.status) {
    is NotException -> this
    else -> when(other.status){
        is ExceptionStatus -> throw other.status.ex
        else -> other
    }
}

fun <E> throwResponse(throwable: Throwable): ThrowableResponse<E>
    = Response(null, ExceptionStatus(throwable))
fun <E> notException(value: E): ThrowableResponse<E>
    = response(value, NotException())


typealias ThrowableResponse<E> = Response<E, ThrowableStatus>
