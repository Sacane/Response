package fr.sacane.response

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class StatusTest {

    internal class FakeCustomStatus: Status() {
        override fun isSuccess(): Boolean  = false
        override fun isFailure(): Boolean = false
    }
    internal class GoodCustomStatus: Status() {
        override fun isSuccess(): Boolean  = true
        override fun isFailure(): Boolean = false
    }

    @Test
    fun `Custom status should not return same boolean value isSuccess and isFailure`(){
        assertThrows<IllegalArgumentException> { FakeCustomStatus() }
    }

    @Test
    fun `Custom status should not throw when the two value are different`(){
        assertDoesNotThrow { GoodCustomStatus() }
    }
}