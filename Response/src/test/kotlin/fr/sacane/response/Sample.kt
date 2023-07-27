package fr.sacane.response

import fr.sacane.response.factory.failure
import fr.sacane.response.factory.ok

internal infix fun Int.divideBy(other: Int): Response<Int> = when(other){
    0 -> fr.sacane.response.factory.failure("Cannot divide by zero")
    else -> ok(this/other)
}

internal fun build(template: String): Response<String> {
    if(template.isEmpty()) {
        return failure("Cannot build from empty sample")
    }
    return ok("BB-129$template")
}
