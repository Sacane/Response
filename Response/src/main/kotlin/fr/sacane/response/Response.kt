package fr.sacane.response

import fr.sacane.response.status.DefaultStatus

/**
 * Monad to wrap an optional value and its customizable status
 *
 *  Native are simple OK and ERROR status. Every Error status store an error message
 */
open class Response<out E, out T: DefaultStatus>


constructor(
    internal val value: E? = null,
    val status: T
) {
    init {
        require(
            (status.isFailure && value == null) || (status.isOk )
        ) {
            "Response value should be null when status is Error"
        }
    }
    override fun equals(other: Any?): Boolean = when(other) {
        is Response<*, *> -> other.value == value && other.status == status
        else -> false
    }
    override fun hashCode(): Int {
        val result = value?.hashCode() ?: 0
        return result + status.hashCode()
    }
}
