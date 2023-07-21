package fr.sacane.response.functional

import fr.sacane.response.Error
import fr.sacane.response.Ok
import fr.sacane.response.Response
import fr.sacane.response.ok
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import fr.sacane.response.error

class MapTest {

    @Test
    fun `Map function should return null with null entry`(){
        assertThrows<NullPointerException> {
            ok<Int>().map { null }
        }
    }

    @Test
    fun `Map function should transform correctly when status is ok`(){
        val response = ok(1).map { it.toString() }

        assertTrue (
            response.status is Ok &&
            response.hasValue() &&
            response.orElse(null) == "1"
        )
    }

    @Test
    fun `Map function should not transform when status is error`(){
        val response: Response<Int> = error<Int>("error").map { 5 }

        assertTrue (
            response.status is Error &&
            !response.hasValue() &&
            response.orElse(null) == null
        )
    }
}