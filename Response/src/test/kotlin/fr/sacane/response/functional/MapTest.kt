package fr.sacane.response.functional

import fr.sacane.response.*
import fr.sacane.response.divideBy
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import fr.sacane.response.factory.failure
import fr.sacane.response.factory.ok;
import fr.sacane.response.status.DefaultStatus
import org.junit.jupiter.api.assertThrows


class MapTest {

    @Test
    fun `Ok response but empty should not be able to map`() {
        assertThrows<UnsupportedOperationException> {
            ok().map { "it.toString()" }
        }
    }

    @Test
    fun `Map function should transform correctly when status is ok`(){
        val response: Response<Int, DefaultStatus> = ok("value").map { 2 }
        assertTrue (
            response.status.isOk &&
            response.value == 2
        )
    }

    @Test
    fun `Map function should not transform when status is error`(){
        val response = failure<Int>("error").map{ "2" }
        assertTrue (
            response.status is Failure &&
            response.value == null
        )
    }

    @Test
    fun `Simple flatmap test`() {
        val response = (20 divideBy  2).flatMap {
            it divideBy 2
        }
        assertTrue(
            response.status is Ok &&
            response.value == 5
        )
    }
}
