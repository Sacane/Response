package fr.sacane.response

class Response<E> constructor(
    internal val value: E? = null,
    val status: Status
) {

    init {
        require(
            (status is Error && value == null) || (status is Ok )
        ) {
            "Response value should be null when status is Error"
        }
    }
    fun hasValue(): Boolean = value != null


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