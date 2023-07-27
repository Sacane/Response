package fr.sacane.response

sealed interface Status {
    val symbol: String
        get() = when(this) {
            is Ok -> "OK"
            is Failure -> "ERROR"
        }
}
open class Ok: Status
open class Failure(val message: String): Status {
    override fun toString(): String {
        return "$symbol(message='$message')"
    }
}
