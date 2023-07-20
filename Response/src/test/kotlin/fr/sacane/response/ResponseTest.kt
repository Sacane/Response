package fr.sacane.response

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ResponseTest {

    @Test
    fun `Response should return success when it is and failure when its not`(){
        assertTrue {
            val response: Response<Int> = ok()
            val response2: Response<Int> = error("Default error message")

            response.isSuccess() && response2.isFailure()
        }
    }

    @Test
    fun `Response should return value when it has one`(){
        val response: Response<Int> = ok(1)
        assertTrue {
            response.hasValue()
        }
    }

    @Test
    fun `Or else method should return the value when its success`(){
        assertTrue {
            val response: Response<Int> = ok(1)
            val response2: Response<Int> = error("Default error message")

            response.orElse(2) == 1 && response2.orElse(2) == 2
        }
    }
}