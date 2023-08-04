package fr.sacane.response

import fr.sacane.response.status.Status

internal infix fun Int.divideBy(other: Int): Response<Int, Status> = when(other){
    0 -> failure("Cannot divide by zero")
    else -> success(this/other)
}

internal fun build(template: String): Response<String, Status> {
    if(template.isEmpty()) {
        return failure("Cannot build from empty sample")
    }
    return success("BB-129$template")
}
