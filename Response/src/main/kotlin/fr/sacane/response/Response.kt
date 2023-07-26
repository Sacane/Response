package fr.sacane.response

/**
 * Monad to wrap an optional value and its customizable status
 *
 *  Native are simple OK and ERROR status. Every Error status store an error message
 */
class Response<E>


constructor(
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
    override fun equals(other: Any?): Boolean = when(other) {
        is Response<*> -> other.value == value && other.status == status
        else -> false
    }
    override fun hashCode(): Int {
        val result = value?.hashCode() ?: 0
        return result + status.hashCode()
    }
}
