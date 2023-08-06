package fr.sacane.response.functional
import fr.sacane.response.*
import fr.sacane.response.status.Status

private fun <T, E: Status> Response<T, E>.validateMapping(): Boolean =
    (status.isSuccess && value != null) || status.isFailure

/**
 * Apply the mapping function [transform] to the value of this response.
 */
fun <T, R, E: Status> Response<T, E>.map(transform: (T) -> R): Response<R, E>{
    if(status.isSuccess && value == null) {
        throw UnsupportedOperationException("Cannot map an empty response")
    }
    return if(this.status.isSuccess) Response(transform(this.value!!), this.status)
    else Response(status= this.status)
}

/**
 * Map this Response to another response using the transform function if this response without the value, or
 * propagate the Error response.
 */
inline infix fun <T, R, S: Status> Response<T, S>.mapEmpty(transform: () -> R): Response<R, S> =
    if(this.status.isSuccess) Response(transform(), this.status) else Response(null, this.status)



/**
 * Map this [Response<V>] to [Response<E>] by applying the [transform]
 * function
 */
fun <V, E, T: Status> Response<V, T>.flatMap(transform: (V) -> Response<E, T>): Response<E, T> {
    check(validateMapping()){
        "Cannot map an empty Response"
    }
    return if(this.status.isSuccess) transform(value!!) else Response(null, this.status)
}

fun <V, E: Status, T: Status, S: T> Response<V, E>.mapStatus(successStatus: S, failureStatus: S): Response<V, T> {
    require(successStatus.isSuccess && failureStatus.isFailure) {
        "Success Status mapping should be OK and failure should be FAILURE"
    }
    return if(status.isSuccess) {
        Response(this.value, successStatus)
    } else {
        Response(null, failureStatus)
    }
}

/**
 * <E> Type of response's wrapped value
 * <T> Type of the initial accumulator
 */
fun <E, T, S: Status> Response<E, S>.fold(
    onSuccess: (E) -> T,
    onFailure: (S) -> T
): T? =
    if(this.status.isSuccess && this.value == null) null
    else if(this.status.isSuccess) onSuccess(value!!)
    else onFailure(status)
