package fr.sacane.response.functional
import fr.sacane.response.Ok
import fr.sacane.response.build
import fr.sacane.response.Response
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class OrTest {
    @Test
    fun `Or should select the right Response`() {
        val response: Response<String> = build("").or(build("CUSTOMER_1726"))
        val response3: Response<String> = build("CUSTOMER")
        val response4: Response<String> = build("")
        val compute: Response<String> = response3.or(response4)

        assertTrue {
            response.status is Ok &&
            response.value == build("CUSTOMER_1726").value &&
            compute.status is Ok && compute.value == build("CUSTOMER").value
        }
    }
}
