package fr.sacane.response.status

interface DefaultStatus {
    val isOk: Boolean
    val isFailure: Boolean
    val message: String?
}
