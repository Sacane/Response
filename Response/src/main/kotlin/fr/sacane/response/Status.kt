package fr.sacane.response

import fr.sacane.response.status.DefaultStatus


sealed interface Status: DefaultStatus {
    val symbol: String
        get() = when(this) {
            is Ok -> "OK"
            is Failure -> "ERROR"
        }
}
open class Ok(
    override val isOk: Boolean = true,
    override val isFailure: Boolean = false
) : Status {
    override val message: String?
        get() = null
}

open class Failure(
    override val message: String,
    override val isOk: Boolean = false,
    override val isFailure: Boolean = true
): Status {
    override fun toString(): String {
        return "$symbol(message='$message')"
    }
}
