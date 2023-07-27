package fr.sacane.response.functional

import fr.sacane.response.Failure
import fr.sacane.response.Response
import fr.sacane.response.divideBy
import fr.sacane.response.factory.failure
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OnTest {

    class Wrap(var value: Int)

    @Test
    fun `Simple on success test`(){
        val wrapper = Wrap(10)
        val response : Response<Int> = (10 divideBy 2)

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
