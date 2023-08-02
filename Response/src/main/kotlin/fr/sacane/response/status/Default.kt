package fr.sacane.response.status

abstract class DefaultStatus(val isOk: Boolean, val isFailure: Boolean) {
    abstract val message: String?

    init {
        require(isOk != isFailure) {
            "status cannot be ok and failure at the same time"
        }
    }
}
