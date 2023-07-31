package fr.sacane.response

import fr.sacane.response.factory.failure
import fr.sacane.response.factory.ok
import fr.sacane.response.status.DefaultStatus

internal infix fun Int.divideBy(other: Int): Response<Int, DefaultStatus> = when(other){
    0 -> failure("Cannot divide by zero")
    else -> ok(this/other)
}

internal fun build(template: String): Response<String, DefaultStatus> {
    if(template.isEmpty()) {
        return failure("Cannot build from empty sample")
    }
    return ok("BB-129$template")
}
