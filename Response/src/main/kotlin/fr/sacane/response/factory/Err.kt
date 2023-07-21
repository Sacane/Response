package fr.sacane.response.factory

import fr.sacane.response.Response
import fr.sacane.response.Error


fun <E> error(message: String): Response<E> = Response(null, Error(message))


