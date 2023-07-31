package fr.sacane.response.http

import fr.sacane.response.Response

sealed class HttpError (message: String, override val code: Int): HttpStatus {
    override val isFailure: Boolean
        get() = true

    override val isOk: Boolean
        get() = false
}

class NotFound(override val message: String): HttpError(message, 404)

class BadRequest(override val message: String): HttpError(message, 400)
class Unauthorized(override val message: String): HttpError(message, 401)

class Forbidden(override val message: String): HttpError(message, 403)

class InternalServerError(override val message: String): HttpError(message, 500)

fun <E> notFound(message: String): Response<E, HttpStatus> {
    return Response(null, NotFound(message))
}

fun <E> badRequest(message: String): Response<E, HttpStatus> {
    return Response(null, BadRequest(message))
}

fun <E> unauthorized(message: String): Response<E, HttpStatus> {
    return Response(null, Unauthorized(message))
}

fun <E> forbidden(message: String): Response<E, HttpStatus> {
    return Response(null, Forbidden(message))
}

fun <E> internalServerError(message: String): Response<E, HttpStatus> {
    return Response(null, InternalServerError(message))
}
