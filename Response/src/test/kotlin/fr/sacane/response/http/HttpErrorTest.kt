package fr.sacane.response.http

import fr.sacane.response.Response
import fr.sacane.response.functional.andThen
import fr.sacane.response.functional.map
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class HttpErrorTest {

    data class Customer(val id: String, val name: String)

    class FakeRepository {

        private val list: MutableList<Customer> = mutableListOf()

        fun findById(id: String): Response<Customer, HttpStatus> {
            if(id.isEmpty()){
                return badRequest("The id is empty")
            }
            if(!id.startsWith("BB-")){
                return unauthorized("The id is in the wrong format")
            }
            val customer = list.find { customer -> customer.id == id }
            return if (customer == null) notFound("The customer has not been find") else ok(customer)
        }

        fun save(customer: Customer){
            list.add(customer)
        }
    }

    private fun searchAll(firstId: String, secondId: String, thirdId: String): Response<Customer, HttpStatus>{
        val repository = FakeRepository()
        return repository.findById(firstId).andThen {
            repository.findById(secondId)
        } .andThen {
            repository.findById(thirdId)
        }
    }

    @Test
    fun `HttpError Response should be created with a message and a code`() {
        val response: Response<String, HttpStatus> = notFound("Not found")
        assertTrue {
            val status = response.status
            status.code == 404 &&
                    status.message == "Not found"
        }
    }

    @Test
    fun `Map an Http Error Response should always return another Http Error response`() {
        val response = notFound<String>("Not found")
        val mappedResponse = response.map { 10 }
        assertTrue {
            val status = response.status
            response.status.isFailure
            status is NotFound              &&
            status.message == "Not found"   &&
            status.code == 404              &&
            mappedResponse.status.isFailure
        }
    }

    @Test
    fun `Simple Valuable response test`() {
        val repository = FakeRepository()
        repository.save(Customer("BB-1", "Jean"))
        assertTrue {
            repository.findById("").status is BadRequest        &&
            repository.findById("BUAUH").status is Unauthorized &&
            repository.findById("BB-1").status is HttpOk
        }
    }

    fun <E> mapping(response: Response<E, HttpStatus>) {
        when (response.status) {
            is NotFound -> println("not found")
            is Forbidden -> println("forbidden")
            is HttpError -> println("internal servor error")
            is HttpOk -> println("Found ok response ${response.value}")
            else -> println("Not an http response")
        }
    }

    @Test
    fun test(){
        mapping(FakeRepository().findById("BB-2RZ"))
    }
}
