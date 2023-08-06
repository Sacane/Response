package fr.sacane.response.http

open class HttpSuccess (override val code: Int): HttpStatus(true, false, code){
    override val message: String?
        get() = null
}
object Ok: HttpSuccess(200)
class Created (val url: String): HttpSuccess(201)
