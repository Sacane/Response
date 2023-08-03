package fr.sacane.response.factory

import fr.sacane.response.Response
import fr.sacane.response.Failure
import fr.sacane.response.DefaultStatus

fun <E> failure(message: String): Response<E, DefaultStatus> = Response(status= Failure(message))


