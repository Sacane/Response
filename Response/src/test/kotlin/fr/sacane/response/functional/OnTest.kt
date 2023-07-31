package fr.sacane.response.functional

import fr.sacane.response.Failure
import fr.sacane.response.Response
import fr.sacane.response.divideBy
import fr.sacane.response.factory.failure
import fr.sacane.response.status.DefaultStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OnTest {

    class Wrapper(var value: Int)

    @Test
    fun `Simple on success test`(){
        val wrapper = Wrapper(10)
        val response : Response<Int, DefaultStatus> = 10 divideBy 2

        response.onSuccess {
            wrapper.value += it
        }
        assertEquals(wrapper.value, 15)
    }

    @Test
    fun `Simple on failure test`() {
        val response = failure<Int>("Computation failed")
        var message = ""

        response.onFailure { message = it }
        assertEquals((response.status as Failure).message, message)
    }
}
