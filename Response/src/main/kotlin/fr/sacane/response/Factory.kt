package fr.sacane.response

import fr.sacane.response.status.Failure
import fr.sacane.response.status.NativeStatus
import fr.sacane.response.status.Status
import fr.sacane.response.status.Success


fun <E, S: Status> response(value: E? = null, status: S) =
    if(status.isSuccess) Response(Data(value), status)
    else Response(null, status)

fun <E> success(value: E): Response<E, NativeStatus> = Response(Data(value), Success)
fun success(): EmptyResponse<NativeStatus> = response(status= Success)

fun <E, S: Status> success(value: E, status: S): Response<E, S> {
    require(status.isSuccess){
        "Cannot build an OK Response with an status Error"
    }
    return Response(Data(value), status)
}

fun <E> failure(message: String): Response<E, NativeStatus> = Response(status= Failure(message))


typealias EmptyResponse<S> = Response<Nothing, S>