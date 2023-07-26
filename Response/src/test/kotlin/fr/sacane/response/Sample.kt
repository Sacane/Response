package fr.sacane.response

import fr.sacane.response.factory.ok

internal infix fun Int.divideBy(other: Int): Response<Int> = when(other){
    0 -> fr.sacane.response.factory.error("Cannot divide by zero")
    else -> ok(this/other)
}
