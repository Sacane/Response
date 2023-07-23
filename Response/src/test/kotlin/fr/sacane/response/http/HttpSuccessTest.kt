package fr.sacane.response.http

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class HttpSuccessTest {

    @Test
    fun `response with created status has 201 as response code`(){
        assertTrue {
            val response = created()
            val status = response.status
            status is Created && status.code == 201
        }
    }
}