package fr.sacane.response.functional

import fr.sacane.response.Failure
import fr.sacane.response.Ok
import fr.sacane.response.Response
import fr.sacane.response.status.DefaultStatus


infix fun <E, S: DefaultStatus> Response<E, S>.or(response: Response<E, S>): Response<E, S> =
    if(this.status.isOk) this else if(response.status.isOk) response else this


infix fun <E, S: DefaultStatus> Response<E, S>.orElse(defaultValue: E): E = if(this.value == null) defaultValue else value

fun <E, S: DefaultStatus> Response<E, S>.orElseGet(get: () -> E): E = if(this.value == null) get() else value
