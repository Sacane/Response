package fr.sacane.response.functional

import fr.sacane.response.*
import fr.sacane.response.status.NativeStatus
import fr.sacane.response.status.Success
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BindTest {

    @Test
    fun `Binding several success results is OK`() {
        val result: Response<Int, NativeStatus> = binding {
            val firstDivision = (20 divideBy 2).bind()
            val secondDivision = (10 divideBy firstDivision).bind()
            secondDivision
        }
        assertTrue(result.status is Success)
        assertEquals(1, result.value?.value)
    }

    @Test
    fun `Binding with one failure should resolve to a failure response`() {
        val failure: Response<Int, NativeStatus> = binding {
            val operation = success(5).bind()
            val operationFailed = failure<Int>("This operation has failed").bind()
            val secondOperation = success(10).bind()
            operation+ operationFailed + secondOperation
        }

        assertFalse(failure.status is Success)
        assertEquals("This operation has failed", failure.status.message)
    }
}