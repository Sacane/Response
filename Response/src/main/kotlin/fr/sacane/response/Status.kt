package fr.sacane.response

abstract class Status {
    init {
        checkStatusConstraints()
    }
    private fun checkStatusConstraints() {
        if (isSuccess() == isFailure()) {
            throw IllegalArgumentException("isSuccess should return a different value than isFailure")
        }
    }
    abstract fun isSuccess(): Boolean
    abstract fun isFailure(): Boolean

}