package fr.sacane.response.exception

import fr.sacane.response.Response


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

typealias ThrowableResponse<E> = Response<E, ThrowableStatus>

