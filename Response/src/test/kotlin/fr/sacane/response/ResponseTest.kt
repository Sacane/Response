package fr.sacane.response

import org.junit.jupiter.api.Test
import fr.sacane.response.functional.orElseGet
import fr.sacane.response.status.Failure
import fr.sacane.response.status.NativeStatus
import fr.sacane.response.status.Status
import fr.sacane.response.status.Success
import org.junit.jupiter.api.Assertions.*
import java.util.Random

class ResponseTest {

    @Test
    fun `Response should return success when it is and failure when its not`(){
        assertTrue {
            val response: EmptyResponse<Status> = success()
            val response2: Response<Int, NativeStatus> = failure("Default error message")

            response.status is Success && response2.status is Failure
        }
    }

    @Test
    fun `Response should return value when it has one`(){
        val response: Response<Int, NativeStatus> = success(1)
        assertTrue (response.value?.value == 1)
    }

    @Test
    fun `Or else method should return the value when its success`(){
        assertTrue {
            val response: Response<Int, NativeStatus> = success(1)
            val response2: Response<Int, NativeStatus> = failure("Default error message")

            response.orElseGet { 2 } == 1 && response2.orElseGet{3} == 3
        }
    }
    @Test
    fun `If response is error then its value should be null`(){
        val divideResult = 3 divideBy 0
        assertTrue(
            divideResult.status is Failure &&
            divideResult.value == null
        )
    }

    inner class RandomStatus
    internal constructor(
        random: Random = Random(),
        isOk: Boolean = random.nextBoolean()
    ): Status(
        isSuccess = isOk,
        isFailure = !isOk
    ) {
        override val message: String
            get() = "is Ok -> $isSuccess & is Failure -> $isFailure"
    }

    @Test
    fun `customized status implementation test`() {
        for(i in 0..100) {
            val customized = RandomStatus()
            val response = response(2, status = customized)

            if(response.status.isSuccess) {
                assertNotNull(response.value)
            } else {
                assertNull(response.value)
            }
            assertEquals("is Ok -> ${response.status.isSuccess} & is Failure -> ${response.status.isFailure}", response.status.message)

        }
    }
}
