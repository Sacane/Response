package fr.sacane.response.factory

import fr.sacane.response.Response
import fr.sacane.response.Error


fun <E> error(message: String): Response<E> = Response(null, Error(message))


class NotFound(message: String) : Error(message) {
    override val symbol: String
        get() = "NOT_FOUND"
}
class BadRequest(message: String) : Error(message) {
    override val symbol: String
        get() = "BAD_REQUEST"
}
class Unauthorized(message: String) : Error(message) {
    override val symbol: String
        get() = "UNAUTHORIZED"
}
class Forbidden(message: String) : Error(message) {
    override val symbol: String
        get() = "FORBIDDEN"
}

fun <E> notFound(msg: String): Response<E> = Response(null, NotFound(msg))
fun <E> badRequest(msg: String): Response<E> = Response(null, BadRequest(msg))
fun <E> unauthorized(msg: String): Response<E> = Response(null, Unauthorized(msg))
fun <E> forbidden(msg: String): Response<E> = Response(null, Forbidden(msg))


