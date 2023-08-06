package fr.sacane.response.http

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class HttpSuccessTest {

    @Test
    fun `Ok response should return 200 as status code and return body`() {
        assertTrue {
            val response = ok("response body")
            response.status is Ok && (response.status as Ok).code == 200 && response.value == "response body"
        }
    }

    @Test
    fun `Ok response empty should has null value test`() {
        assertTrue {
            val response = ok()
            val status = response.status as Ok
            status.code == 200
        }
    }

    @Test
    fun `Response with created status has 201 as response code`(){
        assertTrue {
            val response = created()
            val status = response.status
            status is Created && status.code == 201
        }
    }

    @Test
    fun `simple not found test`() {
        val http = notFound<Int>("Number not found")
        assertTrue(http.status is NotFound)
    }
}
