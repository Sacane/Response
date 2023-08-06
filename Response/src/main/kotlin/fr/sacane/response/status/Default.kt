package fr.sacane.response.status

import fr.sacane.response.Response

abstract class Status(val isSuccess: Boolean, val isFailure: Boolean) {
    abstract val message: String?
    init {
        require(isSuccess != isFailure) {
            "status cannot be ok and failure at the same time"
        }
    }
}

class Secure: Status(true, false) {
    override val message: String
        get() = "This can never fail"
}

typealias SecureResponse<E> = Response<E, Secure>