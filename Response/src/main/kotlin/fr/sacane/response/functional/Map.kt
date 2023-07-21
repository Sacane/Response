package fr.sacane.response.functional
import fr.sacane.response.Response
import fr.sacane.response.ok

fun <T, R> Response<T>.map(transform: (T) -> R): Response<R> = when (this.isSuccess()) {
    true-> ok(transform(this.orElse(null)!!))
    false -> error(this.message())
}