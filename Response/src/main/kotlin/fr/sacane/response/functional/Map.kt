package fr.sacane.response.functional
import fr.sacane.response.*
import fr.sacane.response.factory.ok

private fun <T> Response<T>.validateMapping(): Boolean =
    (status is Ok && value != null) || status is Error

fun <T, R> Response<out T>.map(transform: (T) -> R): Response<out R>{
    if(status is Ok && value == null) {
        throw UnsupportedOperationException("Cannot map an empty response")
    }
    return when (this.status) {
        is Ok -> ok(transform(this.value!!))
        is Error -> Response(null, this.status)
    }
}

/**
 * Map this Response to another response using the transform function if this response is ok, or
 * propagate the Error response.
 */
inline infix fun <T, R> Response<T>.mapEmpty(transform: () -> R): Response<R> {
    return when(this.status) {
        is Ok -> ok(transform())
        is Error -> Response(null, this.status)
    }
}


/**
 * Map this [Response<V>] to [Response<E>] by applying the [transform]
 * function
 */
fun <V, E> Response<V>.flatMap(transform: (V) -> Response<E>): Response<E> {
    check(validateMapping()){
        "Cannot map an empty Response"
    }
    return when(this    .status) {
        is Ok -> transform(value!!)
        is Error -> Response(null, this.status)
    }
}
