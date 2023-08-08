package fr.sacane.response

import fr.sacane.response.status.Status

/**
 * Monad to wrap an optional value and its customizable status.
 */
open class Response<E, out T: Status> internal constructor(
    internal val data: Data<E>? = null,
    val status: T
) {
    init {
        require(
            (status.isFailure && data == null) || (status.isSuccess && data != null)
        ) {
            "Response data should be null when status is Error, not if it's a success status"
        }
    }
    override fun equals(other: Any?): Boolean = when(other) {
        is Response<*, *> -> other.data == data && other.status == status
        else -> false
    }
    override fun hashCode(): Int {
        val result = data?.hashCode() ?: 0
        return result + status.hashCode()
    }
}

data class Data<E>(val value: E?){
    companion object{
        fun <E> empty(): Data<E> = Data(null)
    }
}