package fr.sacane.response

class Response<E> internal constructor(
    private val value: E? = null,
    private val status: Status
) {
    fun isSuccess(): Boolean = status.isSuccess()
    fun isFailure(): Boolean = status.isFailure()
    fun hasValue(): Boolean = value != null

    fun orElse(defaultValue: E): E = when(status.isSuccess()) {
        true -> value!!
        false -> defaultValue
    }
    fun message(): String = status.message ?: "No specific message for this status"
}

fun <E> ok(value: E): Response<E> = Response(value, Ok())
fun <E> ok(): Response<E> = Response(null, Ok())

fun <E> error(message: String): Response<E> = Response(null, Error(message))