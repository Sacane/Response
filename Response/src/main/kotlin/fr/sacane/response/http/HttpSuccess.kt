package fr.sacane.response.http

import fr.sacane.response.Response
import fr.sacane.response.status.DefaultStatus

sealed interface HttpStatus: DefaultStatus {
    val code: Int
}

sealed class HttpSuccess (override val code: Int): HttpStatus{
    override val message: String?
        get() = null
}

class HttpOk: HttpSuccess(200) {
    override val isOk: Boolean
        get() = true
    override val isFailure: Boolean
        get() = false
}

class Created (val url: String): HttpSuccess(201) {
    override val isOk: Boolean
        get() = true
    override val isFailure: Boolean
        get() = false
}

fun created(): Response<Nothing, HttpStatus> = Response(null, Created(""))
fun created(url: String): Response<Nothing, HttpStatus> = Response(null, Created(url))
fun <E> created(url: String, value: E): Response<E, HttpStatus> = Response(value, Created(url))

fun <E> httpOk(body: E): Response<E, HttpStatus> = Response(body, HttpOk())
fun httpOk(): Response<Nothing, HttpStatus> = Response(null, HttpOk())

