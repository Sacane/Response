package fr.sacane.response

sealed interface Status {
    fun isSuccess(): Boolean = when(this) {
        is Ok -> true
        is Error -> false
    }
    fun isFailure(): Boolean = when(this) {
        is Ok -> false
        is Error -> true
    }
}

private fun Status.check() {
    if(!isSuccess() && !isFailure()) {
        throw IllegalStateException("Status should return true for isSuccess or isFailure")
    }
}

open class Ok: Status {
    init {
        check()
    }
}
open class Error(val message: String): Status {
    init {
        check()
    }
}