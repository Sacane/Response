package fr.sacane.response.functional

import fr.sacane.response.*
import fr.sacane.response.divideBy
import fr.sacane.response.status.Status
import fr.sacane.response.status.Success
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class AndTest {

    @Test
    fun `And method should return Failure when one of the response is Failure`() {
        val division: Response<Int, Status> = (10 divideBy 2).and(20 divideBy 0)

        assertTrue(division.status.isFailure)
    }

    @Test
    fun `And method should return OK when all the response are OK`() {
        val response = (10 divideBy 2).and(20 divideBy 2).and {
            (2 divideBy 2)
        }

        assertTrue (response.status is Success)
        assertEquals(1, response.value?.value)
    }

    @Test
    fun `And then method should apply the function after the first response`() {
        val andThenResponse = (20 divideBy 10)
            .andThen { it divideBy 2 }

        assertTrue(
            andThenResponse.status is Success &&
            andThenResponse.value?.value == 1
        )
    }
}
