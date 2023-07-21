package fr.sacane.response.functional

import fr.sacane.response.Error
import fr.sacane.response.Ok
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import fr.sacane.response.factory.error
import fr.sacane.response.factory.ok
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
        val response = ok(2).map { it.toString() }

        assertTrue (
            response.status is Ok &&
            response.hasValue() &&
            response.orElse(null) == "2"
        )
    }

    @Test
    fun `Map function should not transform when status is error`(){
        val response = error<Int>("error").map{ "2" }
        assertTrue (
            response.status is Error &&
            !response.hasValue() &&
            response.orElse(null) == null
        )
    }
}