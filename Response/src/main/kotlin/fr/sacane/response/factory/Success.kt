package fr.sacane.response.factory

import fr.sacane.response.EmptyOk
import fr.sacane.response.Ok
import fr.sacane.response.Response

class Created : Ok()

fun <E> ok(value: E): Response<E> = Response(value, Ok())
fun ok(): Response<Nothing> = Response(null, EmptyOk())