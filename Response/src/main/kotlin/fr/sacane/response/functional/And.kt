package fr.sacane.response.functional

import fr.sacane.response.Response

/**
 * Apply the transform function for this response.
 *
 * This method is equivalent to the flatMap method.
 */
fun <V, E> Response<V>.andThen(transform: (V) -> Response<E>): Response<E> {
    return flatMap(transform)
}
