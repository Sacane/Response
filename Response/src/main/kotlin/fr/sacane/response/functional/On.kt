package fr.sacane.response.functional

import fr.sacane.response.Failure
import fr.sacane.response.Ok
import fr.sacane.response.Response

fun <E> Response<E>.onSuccess(action: (E) -> Unit): Response<E>{
    if(this.status is Ok && this.value != null){
        action(this.value)
    }
    return this
}

fun <E> Response<E>.onFailure(action: (String) -> Unit): Response<E> {
    if(this.status is Failure) {
        action(this.status.message)
    }
    return this
}
