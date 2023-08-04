package fr.sacane.response.functional

import fr.sacane.response.Failure
import fr.sacane.response.Success
import fr.sacane.response.Response
import fr.sacane.response.divideBy
import fr.sacane.response.exception.ExceptionStatus
import fr.sacane.response.exception.NotException
import fr.sacane.response.exception.ThrowableResponse
import fr.sacane.response.failure
import fr.sacane.response.http.*
import fr.sacane.response.success
import fr.sacane.response.status.Status
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class MapTest {

    @Test
    fun `Ok response but empty should not be able to map`() {
        assertThrows<UnsupportedOperationException> {
            success().map { "it.toString()" }
        }
    }

    @Test
    fun `Map function should transform correctly when status is ok`(){
        val response: Response<Int, Status> = success("value").map { 2 }
        assertTrue (
            response.status.isSuccess &&
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
            response.status is Success &&
            response.value == 5
        )
    }

    @Test
    fun `Simple mapEmpty test`() {
        val response = (20 divideBy 2).mapEmpty { 3 }
        assertTrue(response.value == 3)
    }

    @Test
    fun `Response Nothing and mapEmpty can work each other`(){
        val responseNothing = success()
        assertTrue(responseNothing.mapEmpty { 3 }.value == 3)
    }

    @Test
    fun `Response Failure cannot map Empty`() {
        val failure = failure<Int>("Random failure message")
        assertTrue(failure.value == null && failure.mapEmpty { 3 } == failure)
    }

    @Test
    fun `Simple mapping status test`() {
        val response = success(10).mapStatus(Ok(), NotFound("Value cannot be retrieve"))
        assertTrue(response.status.isSuccess && response.status is Ok)
        assertEquals(10, response.value)
    }

    @Test
    fun `Even if Response is Empty, mapStatus should be success`() {
        val response = success().mapStatus(Ok(), NotFound("Value cannot be retrieve"))
        assertTrue(response.status is Ok)
        assertNull(response.value)
    }

    @Test
    fun `If response is failure, mapStatus should respond with mapped failure`(){
        val response: ThrowableResponse<Int> = (20 divideBy 10)
            .mapStatus(
                NotException(),
                ExceptionStatus(UnsupportedOperationException())
            )

        assertTrue(response.status is NotException)
        assertEquals(2, response.value)
    }


    @Test
    fun `If Response is Success, fold should return transform value`() {
        val response = success("OK").fold(
            {v -> if(v == "OK") 10 else 20},
            {_ -> 25}
        )
        assertEquals(10, response)
    }
}
