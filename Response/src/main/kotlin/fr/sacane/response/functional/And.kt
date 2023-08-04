package fr.sacane.response.functional

import fr.sacane.response.*
import fr.sacane.response.status.Status

/**
 * Apply the transform function for this response.
 *
 * This method is equivalent to the flatMap method.
 */
infix fun <V, E, S: Status> Response<V, S>.andThen(
    transform: (V) -> Response<E, S>
): Response<E, S> = flatMap(transform)



infix fun <E, T: Status> Response<E, T>.and(
    result: Response<E, T>
): Response<E, T> = if(this.status.isSuccess) result else this

 infix fun <E, S: Status> Response<E, S>.and(
    actionResponse: () -> Response<E, S>
): Response<E, S> = and(actionResponse())
