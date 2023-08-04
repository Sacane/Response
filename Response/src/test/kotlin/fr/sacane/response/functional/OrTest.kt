package fr.sacane.response.functional
import fr.sacane.response.*
import fr.sacane.response.build
import fr.sacane.response.divideBy
import fr.sacane.response.exception.ExceptionStatus
import fr.sacane.response.exception.NotException
import fr.sacane.response.exception.ThrowableResponse
import fr.sacane.response.exception.ThrowableStatus
import fr.sacane.response.status.Status
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class OrTest {
    @Test
    fun `If At least one of the response is Ok, the result is OK`() {
        val response: Response<String, Status> = build("").or(build("CUSTOMER_1726"))
        val response3: Response<String, Status> = build("CUSTOMER")
        val response4: Response<String, Status> = build("")
        val compute: Response<String, Status> = response3.or(response4)

        assertTrue {
            response.status is Success &&
            response.value == build("CUSTOMER_1726").value &&
            compute.status is Success && compute.value == build("CUSTOMER").value
        }
    }

    @Test
    fun `If All response are Failure, result is Failure`(){
        val response: Response<Int, Status> = (10 divideBy 0) or (5 divideBy 0) or (9 divideBy 0)

        assertTrue {
            response.status is Failure
        }
    }

    @Test
    fun `orElseGet should retrieve the response value if its ok`() {
        val response = (10 divideBy 2).orElseGet { 6 }
        assertTrue(response == 5)
    }

    @Test
    fun `orElseGet should retrieve the default value if response is Error`() {
        val response = (10 divideBy 0).orElseGet {
            (0..5).sum()
        }
        assertEquals(response, 15)
    }

    @Test
    fun `common OrElseThrow method should not throw when status is Success`() {
        val response = (10 divideBy 2)
        val value = assertDoesNotThrow { response.orElseThrow(UnsupportedOperationException()) }
        assertTrue(value == 5)
    }

    @Test
    fun `common OrElseThrow method should throw when status is Failure`() {
        val response = (10 divideBy 0)
        assertThrows<UnsupportedOperationException> {
            response.orElseThrow(UnsupportedOperationException())
        }
    }

    @Test
    fun `orElseThrow should from throwableStatus response throw when Response is failure`() {
        val response: ThrowableResponse<Int> = Response(null, ExceptionStatus(UnsupportedOperationException()))
        assertThrows<UnsupportedOperationException> {
            response.orElseThrow()
        }
    }

    @Test
    fun `orElseThrow from throwableStatus response should not throw when Response is Success`() {
        val response: ThrowableResponse<Int> = Response(2, NotException())
        val value = assertDoesNotThrow {
            response.orElseThrow()
        }
        assertTrue(value == 2)
    }

}
