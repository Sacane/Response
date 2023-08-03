package fr.sacane.response

import fr.sacane.response.factory.success;
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import fr.sacane.response.factory.failure
import fr.sacane.response.functional.orElse
import fr.sacane.response.status.Status
import java.util.Random

class ResponseTest {

    @Test
    fun `Response should return success when it is and failure when its not`(){
        assertTrue {
            val response: Response<Nothing, Status> = success()
            val response2: Response<Int, DefaultStatus> = failure("Default error message")

            response.status is Success && response2.status is Failure
        }
    }

    @Test
    fun `Response should return value when it has one`(){
        val response: Response<Int, DefaultStatus> = success(1)
        assertTrue (response.value == 1)
    }

    @Test
    fun `Or else method should return the value when its success`(){
        assertTrue {
            val response: Response<Int, DefaultStatus> = success(1)
            val response2: Response<Int, DefaultStatus> = failure("Default error message")

            response.orElse(2) == 1 && response2.orElse(3) == 3
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
        isOk = isOk,
        isFailure = !isOk
    ) {
        override val message: String
            get() = "is Ok -> $isOk & is Failure -> $isFailure"
    }

    @Test
    fun `customized status implementation test`() {
        val customized = RandomStatus()
        val response = Response<Int, RandomStatus>(status = customized)

        assertTrue(response.value == null)
        assertTrue {
            response.status.message == "is Ok -> ${response.status.isOk} & is Failure -> ${response.status.isFailure}"
        }
    }


}
