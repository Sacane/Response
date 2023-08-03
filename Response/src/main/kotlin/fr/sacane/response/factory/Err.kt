package fr.sacane.response.factory

import fr.sacane.response.Response
import fr.sacane.response.Failure
import fr.sacane.response.Status

fun <E> failure(message: String): Response<E, Status> = Response(status= Failure(message))


