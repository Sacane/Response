package fr.sacane.response.http.functional

import fr.sacane.response.Response
import fr.sacane.response.http.HttpError

data class ErrorResponse(val message: String, val code: Int)

fun <E> Response<E>.onHttpError(action: (ErrorResponse) -> Unit): Response<E> {
    when(this.status) {
        is HttpError -> action(ErrorResponse(status.message, status.code))
        else -> {}
    }
    return this
}
