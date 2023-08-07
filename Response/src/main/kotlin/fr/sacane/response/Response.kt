package fr.sacane.response

import fr.sacane.response.status.Status

/**
 * Monad to wrap an optional value and its customizable status.
 */
open class Response<E, out T: Status> internal constructor(
    internal val value: Data<E>? = null,
    val status: T
) {
    init {
        require(
            (status.isFailure && value == null) || (status.isSuccess && value != null)
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

data class Data<E>(val value: E?)