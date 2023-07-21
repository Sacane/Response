package fr.sacane.response.functional

import fr.sacane.response.Error
import fr.sacane.response.Ok
import fr.sacane.response.ok
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import fr.sacane.response.error

class MapTest {

    @Test
    fun `Map function should transform correctly when status is ok`(){
        val response = ok<Int>().mapIfPresent({ it.toString() }, "2")

        assertTrue (
            response.status is Ok &&
            response.hasValue() &&
            response.orElse(null) == "2"
        )
    }

    @Test
    fun `Map function should not transform when status is error`(){
        val response = error<Int>("error").mapIfPresent({ "2" }, "2")
        assertTrue (
            response.status is Error &&
            !response.hasValue() &&
            response.orElse(null) == null
        )
    }
}