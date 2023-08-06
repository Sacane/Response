package fr.sacane.response

import fr.sacane.response.status.Status

/**
 * Monad to wrap an optional value and its customizable status.
 */
open class Response<out E, out T: Status>(
    internal val value: E? = null,
    val status: T
) {
    init {
        require(
            (status.isFailure && value == null) || (status.isSuccess )
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
