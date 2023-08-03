package fr.sacane.response.factory

import fr.sacane.response.Ok
import fr.sacane.response.Response
import fr.sacane.response.DefaultStatus
import fr.sacane.response.status.Status

fun <E> ok(value: E): Response<E, DefaultStatus> = Response(value, Ok())
fun ok(): Response<Nothing, DefaultStatus> = Response(status= Ok())

fun <E, S: Status> ok(value: E, status: S): Response<E, S> {
    require(status.isOk){
        "Cannot build an OK Response with an status Error"
    }
    return Response(value, status)
}
