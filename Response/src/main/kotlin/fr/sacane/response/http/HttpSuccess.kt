package fr.sacane.response.http

import fr.sacane.response.status.Status

sealed class HttpStatus(isOk: Boolean, isFailure: Boolean, open val code: Int): Status(isOk, isFailure)

open class HttpSuccess (override val code: Int): HttpStatus(true, false, code){
    override val message: String?
        get() = null
}

open class HttpError (override val message: String?, override val code: Int): HttpStatus(false, true, code)

class Ok: HttpSuccess(200)

class Created (val url: String): HttpSuccess(201)


