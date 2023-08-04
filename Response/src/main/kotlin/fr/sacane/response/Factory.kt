package fr.sacane.response

import fr.sacane.response.status.Status

fun <E> success(value: E): Response<E, DefaultStatus> = Response(value, Success())
fun success(): Response<Nothing, DefaultStatus> = Response(status= Success())

fun <E, S: Status> success(value: E, status: S): Response<E, S> {
    require(status.isOk){
        "Cannot build an OK Response with an status Error"
    }
    return Response(value, status)
}


fun <E> failure(message: String): Response<E, DefaultStatus> = Response(status= Failure(message))


