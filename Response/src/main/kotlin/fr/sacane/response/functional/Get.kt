package fr.sacane.response.functional

import fr.sacane.response.Response
import fr.sacane.response.status.Status

fun <E> Response<E, Status>.get(): E {
    check(value != null && status.isSuccess) {
        "The value should not be null and response not empty"
    }
    return value
}