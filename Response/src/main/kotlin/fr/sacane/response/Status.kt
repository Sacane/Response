package fr.sacane.response

sealed interface Status {
    fun isSuccess(): Boolean
    fun isFailure(): Boolean
}

internal fun Status.check(){
    if(isSuccess() == isFailure()) throw IllegalStateException("Status should not be able to be both success and failure")
}

open class Ok: Status{
    init {
        check()
    }
    private fun check(){
        if(isSuccess() == isFailure()) throw IllegalStateException("Status should not be able to be both success and failure")
    }
    override fun isSuccess(): Boolean = true
    override fun isFailure(): Boolean = false
}

open class Error(val message: String): Status{
    init {
        check()
    }
    override fun isSuccess(): Boolean = false
    override fun isFailure(): Boolean = true
}