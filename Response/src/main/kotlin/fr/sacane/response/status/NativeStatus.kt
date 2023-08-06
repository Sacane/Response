package fr.sacane.response.status

import fr.sacane.response.Response


open class NativeStatus(
    isOk: Boolean,
    isFailure: Boolean,
    override val message: String? = null
): Status(isOk, isFailure)

object Success: NativeStatus(true, false)

class Failure(override val message: String): NativeStatus(false, true, message)

object Secure: NativeStatus(true, false) {
    override val message: String
        get() = "This can never fail"
}

typealias SecureResponse<E> = Response<E, Secure>