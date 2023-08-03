package fr.sacane.response.http

import fr.sacane.response.Response
import fr.sacane.response.status.Status

sealed class HttpStatus(isOk: Boolean, isFailure: Boolean, open val code: Int): Status(isOk, isFailure)

open class HttpSuccess (override val code: Int): HttpStatus(true, false, code){
    override val message: String?
        get() = null
}

open class HttpError (override val message: String?, override val code: Int): HttpStatus(false, true, code) {

}

class HttpOk: HttpSuccess(200)

class Created (val url: String): HttpSuccess(201)

fun created(): Response<Nothing, HttpStatus> = Response(null, Created(""))
fun created(url: String): Response<Nothing, HttpStatus> = Response(null, Created(url))
fun <E> created(url: String, value: E): Response<E, HttpStatus> = Response(value, Created(url))

fun <E> httpOk(body: E): Response<E, HttpStatus> = Response(body, HttpOk())
fun httpOk(): Response<Nothing, HttpStatus> = Response(null, HttpOk())

