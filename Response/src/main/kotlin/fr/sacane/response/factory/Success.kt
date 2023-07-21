package fr.sacane.response.factory

import fr.sacane.response.EmptyOk
import fr.sacane.response.Ok
import fr.sacane.response.Response

class Created internal constructor(val url: String): Ok()
class EmptyCreated internal constructor(val url: String): EmptyOk()
fun <E> created(url: String, value: E) = Response(value, Created(url))
fun created() = Response(null, EmptyCreated(""))

fun <E> ok(value: E): Response<E> = Response(value, Ok())
fun ok(): Response<Nothing> = Response(null, EmptyOk())

