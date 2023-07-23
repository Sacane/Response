package fr.sacane.response.functional
import fr.sacane.response.*
import ok

fun <T, R> Response<out T>.map(transform: (T) -> R): Response<out R> = when (this.status) {
    is EmptyOk -> throw UnsupportedOperationException("Cannot map an empty response")
    is Ok -> ok(transform(this.orElse(null)!!))
    is Error -> Response(null, this.status)
}