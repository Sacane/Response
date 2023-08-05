package fr.sacane.response.http

import fr.sacane.response.Response

fun <E> notFound(message: String): HttpResponse<E>
    = Response(null, NotFound(message))
fun <E> badRequest(message: String): HttpResponse<E>
= Response(null, BadRequest(message))


fun <E> unauthorized(message: String): HttpResponse<E>
    = Response(null, Unauthorized(message))


fun <E> forbidden(message: String): HttpResponse<E>
    = Response(null, Forbidden(message))


fun <E> internalServerError(message: String): HttpResponse<E>
    = Response(null, InternalServerError(message))

fun created(): HttpResponse<Nothing> = Response(null, Created(""))
fun created(url: String): HttpResponse<Nothing> = Response(null, Created(url))
fun <E> created(url: String, value: E): HttpResponse<E> = Response(value, Created(url))

fun <E> ok(body: E): HttpResponse<E> = Response(body, Ok())
fun ok(): HttpResponse<Nothing> = Response(null, Ok())
