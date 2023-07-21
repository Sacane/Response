package fr.sacane.response

import fr.sacane.response.factory.created
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CoreTest {
    @Test
    fun `Ok response code should returns 200 and 201 when created`() {
        val ok = Ok()
        val created = created()
        assertEquals(200, responseCode(ok))
        assertEquals(201, responseCode(created.status))
        assertEquals(201, responseCode(created("http://localhost:8080/resource", 1).status))
    }

    @Test
    fun `Error response code should returns 500`() {
        val error = Error("error")
        assertEquals(500, responseCode(error))
    }
}