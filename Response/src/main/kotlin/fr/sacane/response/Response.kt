package fr.sacane.response

class Response<E> internal constructor(
    private val value: E? = null,
    private val status: Status
) {
    fun isSuccess(): Boolean = status.isSuccess()
    fun isFailure(): Boolean = status.isFailure()
    fun hasValue(): Boolean = value != null

    fun orElse(defaultValue: E?): E? = when(status.isSuccess()) {
        true -> value ?: defaultValue
        false -> defaultValue
    }
    fun message(): String = when(status) {
        is Error -> status.message
        is Ok -> "No specific message for this status"
    }

    override fun equals(other: Any?): Boolean = when(other) {
        is Response<*> -> other.value == value && other.status == status
        else -> false
    }

    override fun hashCode(): Int {
        var result = value?.hashCode() ?: 0
        return result + status.hashCode()
    }
}

fun <E> ok(value: E): Response<E> = Response(value, Ok())
fun <E> ok(): Response<E> = Response(null, Ok())

fun <E> error(message: String): Response<E> = Response(null, Error(message))