package fr.sacane.response.functional

import fr.sacane.response.Response
import fr.sacane.response.exception.ExceptionStatus
import fr.sacane.response.exception.NotException
import fr.sacane.response.exception.ThrowableResponse
import fr.sacane.response.status.Status

/**
 *
 */
infix fun <E, S: Status> Response<E, S>.or(response: Response<E, S>): Response<E, S> =
    if(this.status.isSuccess) this else if(response.status.isSuccess) response else this

fun <E, S: Status> Response<E, S>.orElseGet(get: () -> E): E
        = if(this.data == null) get() else data.value!!

fun <E, S: Status> Response<E, S>.orElseThrow(throwable: Throwable)
    = if(status.isFailure || data == null) throw throwable else data

fun <E, S: Status> Response<E, S>.orElseThrow(): E =
    if(status.isFailure || data == null || data.value == null) throw NoSuchElementException()
    else data.value

fun <E> ThrowableResponse<E>.throwOnFailure(): E = when(this.status) {
    is ExceptionStatus -> throw this.status.ex
    is NotException -> data?.value ?: throw NoSuchElementException()
}
