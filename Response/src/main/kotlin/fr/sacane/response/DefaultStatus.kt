package fr.sacane.response

import fr.sacane.response.status.Status


open class DefaultStatus(
    isOk: Boolean,
    isFailure: Boolean,
    override val message: String? = null
): Status(isOk, isFailure)

class Success: DefaultStatus(true, false)

class Failure(override val message: String): DefaultStatus(false, true, message)