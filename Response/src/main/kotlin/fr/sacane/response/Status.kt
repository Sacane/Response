package fr.sacane.response

sealed interface Status {
    val symbol: String
        get() = when(this) {
            is Ok -> "OK"
            is Error -> "ERROR"
        }
}


open class Ok: Status
open class Error(val message: String): Status {
    override fun toString(): String {
        return "$symbol(message='$message')"
    }
}

open class EmptyOk: Ok()