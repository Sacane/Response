package fr.sacane.response.functional

import fr.sacane.response.Failure
import fr.sacane.response.Ok
import fr.sacane.response.Response

/**
 * Apply the transform function for this response.
 *
 * This method is equivalent to the flatMap method.
 */
infix fun <V, E> Response<V>.andThen(
    transform: (V) -> Response<E>
): Response<E> = flatMap(transform)



fun <E> Response<E>.and(
    result: Response<E>
): Response<E> = when(this.status) {
    is Ok -> result
    is Failure -> this
}

inline infix fun <E> Response<E>.and(
    actionResponse: () -> Response<E>
): Response<E> = and(actionResponse())
