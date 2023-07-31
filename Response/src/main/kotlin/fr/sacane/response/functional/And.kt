package fr.sacane.response.functional

import fr.sacane.response.*
import fr.sacane.response.status.DefaultStatus

/**
 * Apply the transform function for this response.
 *
 * This method is equivalent to the flatMap method.
 */
infix fun <V, E, S: DefaultStatus> Response<V, S>.andThen(
    transform: (V) -> Response<E, S>
): Response<E, S> = flatMap(transform)



fun <E, T: DefaultStatus> Response<E, T>.and(
    result: Response<E, T>
): Response<E, T> = if(this.status.isOk) result else this

 fun <E, S: DefaultStatus> Response<E, S>.and(
    actionResponse: () -> Response<E, S>
): Response<E, S> = and(actionResponse())
