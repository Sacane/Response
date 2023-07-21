package fr.sacane.response.http

import fr.sacane.response.Error
import fr.sacane.response.functional.map
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class HttpErrorTest {

    @Test
    fun `HttpError Response should be created with a message and a code`() {
        val response = notFound<String>("Not found")
        assertTrue {
            val status = response.status as HttpError
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
            status is NotFound              &&
            status.message == "Not found"   &&
            status.code == 404              &&
            mappedResponse.status is Error
        }
    }
}