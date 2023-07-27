package fr.sacane.response.http

import fr.sacane.response.Failure
import fr.sacane.response.Response
import fr.sacane.response.functional.andThen
import fr.sacane.response.functional.map
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.*

class HttpErrorTest {

    data class Customer(val id: String, val name: String)

    class FakeRepository {

        private val list: MutableList<Customer> = mutableListOf()

        fun findById(id: String): Response<Customer> {
            if(id == ""){
                return notFound("The id is empty")
            }
            if(!id.startsWith("BB-")){
                return unauthorized("The id is in the wrong format")
            }
            return httpOk(Customer(UUID.randomUUID().toString(), "John Doe"))
        }

        fun save(customer: Customer){
            list.add(customer)
        }
    }

    private fun searchAll(firstId: String, secondId: String, thirdId: String): Response<Customer>{
        val repository = FakeRepository()
        return repository.findById(firstId).andThen {
            repository.findById(secondId)
        } .andThen {
            repository.findById(thirdId)
        }
    }

    @Test
    fun `andThen method can be propagated`() {
        val response = searchAll("BB-10", "BB-12", "BB-23")

        assertTrue(response.status is HttpOk)
    }

    @Test
    fun `HttpError Response should be created with a message and a code`() {
        val response = notFound<String>("Not found")
        assertTrue {
            val status = response.status
            status is NotFound            &&
            status.message == "Not found" &&
            status.code == 404
        }
    }

    @Test
    fun `Map an Http Error Response should always return another Http Error response`() {
        val response = notFound<String>("Not found")
        val mappedResponse = response.map { 10 }
        assertTrue {
            val status = response.status as HttpError
            response.status is HttpError
            status is NotFound              &&
            status.message == "Not found"   &&
            status.code == 404              &&
            mappedResponse.status is Failure
        }
    }

    @Test
    fun `Simple Valuable response test`() {
        val repository = FakeRepository()
        assertTrue {
            repository.findById("").status is NotFound          &&
            repository.findById("BUAUH").status is Unauthorized &&
            repository.findById("BB-10291").status is HttpOk
        }
    }
}
