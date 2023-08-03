package fr.sacane.response

import fr.sacane.response.status.Status


open class DefaultStatus(
    isOk: Boolean,
    isFailure: Boolean,
    override val message: String?
): Status(isOk, isFailure)

open class Ok: DefaultStatus(true, false, null)

open class Failure(override val message: String): DefaultStatus(false, true, message)
