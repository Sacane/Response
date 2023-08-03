package fr.sacane.response.functional
import fr.sacane.response.*
import fr.sacane.response.status.Status

private fun <T, E: Status> Response<T, E>.validateMapping(): Boolean =
    (status.isOk && value != null) || status.isFailure

fun <T, R, E: Status> Response<T, E>.map(transform: (T) -> R): Response<R, E>{
    if(status.isOk && value == null) {
        throw UnsupportedOperationException("Cannot map an empty response")
    }
    return if(this.status.isOk) Response(transform(this.value!!), this.status) else Response(status= this.status)
}

/**
 * Map this Response to another response using the transform function if this response is ok, or
 * propagate the Error response.
 */
inline infix fun <T, R, S: Status> Response<T, S>.mapEmpty(transform: () -> R): Response<R, S> =
    if(this.status.isOk) Response(transform(), this.status) else Response(null, this.status)



/**
 * Map this [Response<V>] to [Response<E>] by applying the [transform]
 * function
 */
fun <V, E, T: Status> Response<V, T>.flatMap(transform: (V) -> Response<E, T>): Response<E, T> {
    check(validateMapping()){
        "Cannot map an empty Response"
    }
    return if(this.status.isOk) transform(value!!) else Response(null, this.status)
}
