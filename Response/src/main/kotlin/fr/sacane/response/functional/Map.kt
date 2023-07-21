package fr.sacane.response.functional
import fr.sacane.response.*
import fr.sacane.response.factory.ok
import fr.sacane.response.factory.error

fun <T, R> Response<out T>.map(transform: (T) -> R): Response<out R> = when (this.status) {
    is EmptyOk -> throw UnsupportedOperationException("Cannot map an empty response")
    is Ok -> ok(transform(this.orElse(null)!!))
    is Error -> error(this.message())
}