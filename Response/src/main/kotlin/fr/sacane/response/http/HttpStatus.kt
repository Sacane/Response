package fr.sacane.response.http

import fr.sacane.response.EmptyResponse
import fr.sacane.response.Response
import fr.sacane.response.status.Status

sealed class HttpStatus(isOk: Boolean, isFailure: Boolean, open val code: Int): Status(isOk, isFailure)

typealias HttpResponse<E> = Response<E, HttpStatus>