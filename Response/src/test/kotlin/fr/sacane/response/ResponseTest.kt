package fr.sacane.response

import fr.sacane.response.factory.ok;
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import fr.sacane.response.factory.error
import fr.sacane.response.functional.orElse

class ResponseTest {

    @Test
    fun `Response should return success when it is and failure when its not`(){
        assertTrue {
            val response= ok()
            val response2: Response<Int> = error("Default error message")

            response.status is Ok && response2.status is Error
        }
    }

    @Test
    fun `Response should return value when it has one`(){
        val response: Response<Int> = ok(1)
        assertTrue (response.hasValue())
    }

    @Test
    fun `Or else method should return the value when its success`(){
        assertTrue {
            val response: Response<Int> = ok(1)
            val response2: Response<Int> = error("Default error message")

            response.orElse(2) == 1 && response2.orElse(2) == 2
        }
    }

    

    private fun divide(divider: Int, dividend: Int): Response<Int> {
        if(dividend == 0) return error("Cannot divide by zero")
        return ok(divider / dividend)
    }
}