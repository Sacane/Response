package fr.sacane.response.factory

import fr.sacane.response.Response
import fr.sacane.response.Failure


fun <E> error(message: String): Response<E> = Response(null, Failure(message))


