package fr.sacane.response

import fr.sacane.response.status.DefaultStatus


open class Status(
    isOk: Boolean,
    isFailure: Boolean,
    override val message: String?
): DefaultStatus(isOk, isFailure)

open class Ok: Status(true, false, null)

open class Failure(override val message: String): Status(false, true, message)
