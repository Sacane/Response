package fr.sacane.response

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class StatusTest {

    internal class FakeCustomStatus: Ok() {
        override fun isSuccess(): Boolean  = false
        override fun isFailure(): Boolean = false
    }
    internal class GoodCustomStatus: Ok() {
        override fun isSuccess(): Boolean  = true
        override fun isFailure(): Boolean = false
    }

    @Test
    fun `Custom status should not return same boolean value isSuccess and isFailure`(){
        assertThrows<IllegalStateException> { FakeCustomStatus() }
    }

    @Test
    fun `Custom status should not throw when the two value are different`(){
        assertDoesNotThrow { GoodCustomStatus() }
    }
}