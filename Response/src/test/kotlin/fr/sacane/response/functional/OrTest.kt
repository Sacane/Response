package fr.sacane.response.functional
import fr.sacane.response.*
import fr.sacane.response.build
import fr.sacane.response.divideBy
import fr.sacane.response.status.Status
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class OrTest {
    @Test
    fun `If At least one of the response is Ok, the result is OK`() {
        val response: Response<String, Status> = build("").or(build("CUSTOMER_1726"))
        val response3: Response<String, Status> = build("CUSTOMER")
        val response4: Response<String, Status> = build("")
        val compute: Response<String, Status> = response3.or(response4)

        assertTrue {
            response.status is Ok &&
            response.value == build("CUSTOMER_1726").value &&
            compute.status is Ok && compute.value == build("CUSTOMER").value
        }
    }

    @Test
    fun `If All response are Failure, result is Failure`(){
        val response: Response<Int, Status> = (10 divideBy 0) or (5 divideBy 0) or (9 divideBy 0)

        assertTrue {
            response.status is Failure
        }
    }
}
