package fr.sacane.response.functional

import fr.sacane.response.Response
import fr.sacane.response.exception.ExceptionStatus
import fr.sacane.response.exception.ThrowableResponse
import fr.sacane.response.status.Status


infix fun <E, S: Status> Response<E, S>.or(response: Response<E, S>): Response<E, S> =
    if(this.status.isSuccess) this else if(response.status.isSuccess) response else this

infix fun <E, S: Status> Response<E, S>.orElse(action: (S) -> Unit) {
    if(status.isFailure) action(status)
}

infix fun <E, S: Status> Response<E, S>.orElseGet(defaultValue: E): E
    = if(this.value == null) defaultValue else value

fun <E, S: Status> Response<E, S>.orElseGet(get: () -> E): E
        = if(this.value == null) get() else value

fun <E, S: Status> Response<E, S>.orElseThrow(throwable: Throwable)
    = if(status.isFailure || value == null) throw throwable else value

fun <E, S: Status> Response<E, S>.orElseThrow(): E
    = if(status.isFailure || value == null) throw NoSuchElementException() else value

fun <E> ThrowableResponse<E>.throwOnFailure(): E {
    if(this.status.isFailure || this.value == null) {
        throw (this.status as ExceptionStatus).ex
    }
    return this.value
}
