package fr.sacane.response.functional

import fr.sacane.response.Error
import fr.sacane.response.Ok
import fr.sacane.response.Response

fun <E> Response<E>.orElse(defaultValue: E?): E? = when(status) {
    is Error -> defaultValue
    is Ok -> value
}