package fr.sacane.response.functional

import fr.sacane.response.*
import fr.sacane.response.status.Status

inline fun <E, S: Status> binding(crossinline block: ResponseBinding<S>.() -> E): Response<E, S> {
    val receiver = ResponseBinding<S>()
    return runCatching {
        with(receiver){
            Response(block(), receiver.status)
        }
    }.getOrElse {
        Response(null, receiver.status)
    }
}

class ResponseBinding<S: Status>{
    lateinit var status: S
    fun <E> Response<E, S>.bind() : E {
        this@ResponseBinding.status = this.status
        return if(this.status.isSuccess && this.value != null) {
            this.value
        } else {
            throw BindingException()
        }
    }
}

private class BindingException: Exception()
