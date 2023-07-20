package fr.sacane.response

abstract class Status(val message: String? = null) {
    init {
        checkStatusConstraints()
    }
    private fun checkStatusConstraints() {
        if (isSuccess() == isFailure()) {
            throw IllegalArgumentException("isSuccess should return a different value than isFailure")
        }
        if(isFailure() && message == null) throw IllegalArgumentException("Error status should be instanced with a message")

    }
    abstract fun isSuccess(): Boolean
    abstract fun isFailure(): Boolean
}

class Ok: Status(){
    override fun isSuccess(): Boolean = true
    override fun isFailure(): Boolean = false
}

class Error(message: String?): Status(message){
    override fun isSuccess(): Boolean = false
    override fun isFailure(): Boolean = true
}