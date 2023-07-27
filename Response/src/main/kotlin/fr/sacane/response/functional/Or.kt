package fr.sacane.response.functional

import fr.sacane.response.Failure
import fr.sacane.response.Ok
import fr.sacane.response.Response


fun <E> Response<E>.or(response: Response<E>): Response<E> = when(this.status) {
    is Ok -> this
    is Failure -> when(response.status) {
        is Ok -> response
        is Failure -> this
    }
}

fun <E> Response<E>.orElse(defaultValue: E): E = if(this.value == null) defaultValue else value

fun <E> Response<out E>.orElseGet(get: () -> E): E = if(this.value == null) get() else value
