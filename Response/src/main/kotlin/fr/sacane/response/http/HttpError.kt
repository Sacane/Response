package fr.sacane.response.http

import fr.sacane.response.Error
import fr.sacane.response.Response

sealed class HttpError (message: String, val code: Int): Error(message) {
    override val symbol: String
        get() = "HTTP_ERROR"
}

class NotFound(message: String): HttpError(message, 404) {
    override val symbol: String
        get() = "NOT_FOUND"
}

class BadRequest(message: String): HttpError(message, 400) {
    override val symbol: String
        get() = "BAD_REQUEST"
}

class Unauthorized(message: String): HttpError(message, 401) {
    override val symbol: String
        get() = "UNAUTHORIZED"
}

class Forbidden(message: String): HttpError(message, 403) {
    override val symbol: String
        get() = "FORBIDDEN"
}

class InternalServerError(message: String): HttpError(message, 500) {
    override val symbol: String
        get() = "INTERNAL_SERVER_ERROR"
}

fun <E> notFound(message: String): Response<E> {
    return Response(null, NotFound(message))
}

fun <E> badRequest(message: String): Response<E> {
    return Response(null, BadRequest(message))
}

fun <E> unauthorized(message: String): Response<E> {
    return Response(null, Unauthorized(message))
}

fun <E> forbidden(message: String): Response<E> {
    return Response(null, Forbidden(message))
}

fun <E> internalServerError(message: String): Response<E> {
    return Response(null, InternalServerError(message))
}