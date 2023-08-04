package fr.sacane.response.exception

import fr.sacane.response.status.Status

sealed class ThrowableStatus(isOk: Boolean, isFailure: Boolean, override val message: String?): Status(isOk, isFailure)

class NotException: ThrowableStatus(true, false, null)
class ExceptionStatus(val ex: Throwable): ThrowableStatus(false, true, ex.message)

