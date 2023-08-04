package fr.sacane.response.status

abstract class Status(val isSuccess: Boolean, val isFailure: Boolean) {
    abstract val message: String?
    init {
        require(isSuccess != isFailure) {
            "status cannot be ok and failure at the same time"
        }
    }
}
