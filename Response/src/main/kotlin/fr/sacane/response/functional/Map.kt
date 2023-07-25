package fr.sacane.response.functional
import fr.sacane.response.*
import fr.sacane.response.factory.ok

fun <T, R> Response<out T>.map(transform: (T) -> R): Response<out R>{
    if(status is Ok && value == null) {
        throw UnsupportedOperationException("Cannot map an empty response")
    }
    return when (this.status) {
        is Ok -> ok(transform(this.value!!))
        is Error -> Response(null, this.status)
    }
}