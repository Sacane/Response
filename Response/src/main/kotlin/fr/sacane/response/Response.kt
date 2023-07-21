package fr.sacane.response

class Response<E> internal constructor(
    private val value: E? = null,
    val status: Status
) {
    fun hasValue(): Boolean = value != null

    fun orElse(defaultValue: E?): E? = when(status) {
        is EmptyOk -> defaultValue
        is Ok -> value!!
        is Error -> defaultValue
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
        val result = value?.hashCode() ?: 0
        return result + status.hashCode()
    }
}

fun <E> ok(value: E): Response<E> = Response(value, Ok())
fun ok(): Response<Nothing> = Response(null, EmptyOk())

fun <E> error(message: String): Response<E> = Response(null, Error(message))