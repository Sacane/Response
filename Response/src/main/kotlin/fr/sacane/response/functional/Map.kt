package fr.sacane.response.functional
import fr.sacane.response.Error
import fr.sacane.response.Ok
import fr.sacane.response.Response
import fr.sacane.response.ok

fun <T, R> Response<T>.map(transform: (T) -> R): Response<R> = when (this.status) {
    is Ok -> ok(transform(this.orElse(null)!!))
    is Error -> error(this.message())
}