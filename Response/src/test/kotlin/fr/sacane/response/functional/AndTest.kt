package fr.sacane.response.functional

import fr.sacane.response.Ok
import fr.sacane.response.divideBy
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class AndTest {

    fun `And method should return OK when both response are OK`() {

    }

    @Test
    fun `And then method should apply the function after the first response`() {
        val andThenResponse = (20 divideBy 10)
            .andThen { it divideBy 2 }

        assertTrue(
            andThenResponse.status is Ok &&
            andThenResponse.value == 1
        )
    }
}
