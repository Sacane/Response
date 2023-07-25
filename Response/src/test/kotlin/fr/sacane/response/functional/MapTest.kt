package fr.sacane.response.functional

import fr.sacane.response.Error
import fr.sacane.response.Ok
import fr.sacane.response.Response
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import fr.sacane.response.factory.error
import ok;
import org.junit.jupiter.api.assertThrows
import java.util.*

class MapTest {

    @Test
    fun `Ok response but empty should not be able to map`() {
        assertThrows<UnsupportedOperationException> {
            ok().map { "it.toString()" }
        }
    }

    @Test
    fun `Map function should transform correctly when status is ok`(){
        val response = ok("value").map { it.uppercase(Locale.getDefault()) }
        assertTrue (
            response.status is Ok &&
            response.hasValue() &&
            response.value == "VALUE"
        )
    }

    @Test
    fun `Map function should not transform when status is error`(){
        val response = error<Int>("error").map{ "2" }
        assertTrue (
            response.status is Error &&
            response.value == null
        )
    }
}