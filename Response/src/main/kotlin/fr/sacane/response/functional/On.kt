package fr.sacane.response.functional

import fr.sacane.response.status.Failure
import fr.sacane.response.Response
import fr.sacane.response.status.Success
import fr.sacane.response.status.Status

fun <E, S: Status> Response<E, S>.onSuccess(action: (E) -> Unit): Response<E, S>{
    if(this.status is Success && this.value != null){
        action(this.value)
    }
    return this
}

fun <E, S: Status> Response<E, S>.onFailure(action: (String) -> Unit): Response<E, S> {
    if(this.status is Failure) {
        action(this.status.message)
    }
    return this
}

fun <E, S: Status> Response<E, S>.onErrorThrow(throwable: Throwable): Response<E, S> {
    if(this.status.isFailure){
        throw throwable
    }
    return this
}

