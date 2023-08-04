package fr.sacane.response.http

import fr.sacane.response.EmptyResponse
import fr.sacane.response.Response

fun <E> notFound(message: String): Response<E, HttpStatus>
    = Response(null, NotFound(message))


fun <E> badRequest(message: String): Response<E, HttpStatus>
= Response(null, BadRequest(message))


fun <E> unauthorized(message: String): Response<E, HttpStatus>
    = Response(null, Unauthorized(message))


fun <E> forbidden(message: String): Response<E, HttpStatus>
    = Response(null, Forbidden(message))


fun <E> internalServerError(message: String): Response<E, HttpStatus>
    = Response(null, InternalServerError(message))

fun created(): EmptyResponse<HttpStatus> = Response(null, Created(""))
fun created(url: String): EmptyResponse<HttpStatus> = Response(null, Created(url))
fun <E> created(url: String, value: E): Response<E, HttpStatus> = Response(value, Created(url))

fun <E> ok(body: E): Response<E, HttpStatus> = Response(body, Ok())
fun ok(): EmptyResponse<HttpStatus> = Response(null, Ok())
