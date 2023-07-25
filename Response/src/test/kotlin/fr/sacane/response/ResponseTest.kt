package fr.sacane.response

import fr.sacane.response.factory.ok;
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import fr.sacane.response.factory.error

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

            response.value == 1 && response2.value == null
        }
    }
    @Test
    fun `If response is error then its value should be null`(){
        val divideResult = 3 divideBy 0
        assertTrue(
            divideResult.status is Error &&
            divideResult.value == null
        )
    }
    private infix fun Int.divideBy(other: Int): Response<Int> = when(other){
        0 -> error("Cannot divide by zero")
        else -> ok(this/other)
    }
}