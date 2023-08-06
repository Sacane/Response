package fr.sacane.response.status

abstract class Status(@PublishedApi internal val isSuccess: Boolean, @PublishedApi internal val isFailure: Boolean) {
    abstract val message: String?
    init {
        require(isSuccess != isFailure) {
            "status cannot be ok and failure at the same time"
        }
    }
}