package fr.sacane.response.functional

import fr.sacane.response.failure
import fr.sacane.response.success
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GetTest {


    @Test
    fun `Simple get test`() {
        val response = success("How does it work")
        assertEquals("How does it work", response.get())
    }

    @Test
    fun `Failure throws when trying to get an element`() {
        val failure = failure<Int>("this test fail")
        assertThrows<IllegalStateException> { failure.get() }
    }

    @Test
    fun `Success but empty response should throw after get() call`() {
        val empty = success()
        assertThrows<IllegalStateException> {
            empty.get()
        }
    }
}