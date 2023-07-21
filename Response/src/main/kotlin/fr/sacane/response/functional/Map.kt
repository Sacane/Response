package fr.sacane.response.functional
import fr.sacane.response.Error
import fr.sacane.response.Ok
import fr.sacane.response.Response
import fr.sacane.response.ok
import fr.sacane.response.error

fun <T, R> Response<out T>.mapIfPresent(transform: (T) -> R, defaultValueOnSuccess: R): Response<out R> = when (this.status) {
    is Ok -> if(hasValue()) ok(transform(this.orElse(null)!!)) else ok(defaultValueOnSuccess)
    is Error -> error(this.message())
}