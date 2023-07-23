package fr.sacane.response.http

import fr.sacane.response.EmptyOk
import fr.sacane.response.Ok
import fr.sacane.response.Response

private const val createdSymbol = "HTTP_CREATED"
sealed class HttpSuccess (val code: Int): Ok() {
    override val symbol: String
        get() = createdSymbol
}

sealed class EmptyHttpSuccess (val code: Int): EmptyOk() {
    override val symbol: String
        get() = createdSymbol
}

class Created (val url: String): EmptyHttpSuccess(201)
class CreatedWithBody internal constructor(val url: String): HttpSuccess(201)

fun created() = Response(null, Created(""))
fun created(url: String) = Response(null, Created(url))
fun <E> created(url: String, value: E) = Response(value, CreatedWithBody(url))