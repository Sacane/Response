import fr.sacane.response.Ok
import fr.sacane.response.Response

fun <E> ok(value: E): Response<E> = Response(value, Ok())
fun ok(): Response<Nothing> = Response(null, Ok())

