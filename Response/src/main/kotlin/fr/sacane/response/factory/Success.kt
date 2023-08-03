package fr.sacane.response.factory

import fr.sacane.response.Ok
import fr.sacane.response.Response
import fr.sacane.response.Status
import fr.sacane.response.status.DefaultStatus

fun <E> ok(value: E): Response<E, Status> = Response(value, Ok())
fun ok(): Response<Nothing, Status> = Response(status= Ok())

fun <E, S: DefaultStatus> ok(value: E, status: S): Response<E, S> {
    require(status.isOk){
        "Cannot build an OK Response with an status Error"
    }
    return Response(value, status)
}
