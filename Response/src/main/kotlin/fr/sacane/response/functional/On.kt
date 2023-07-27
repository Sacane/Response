package fr.sacane.response.functional

import fr.sacane.response.Failure
import fr.sacane.response.Ok
import fr.sacane.response.Response

fun <E> Response<E>.onSuccess(action: () -> Unit): Response<E>{
    if(this.status is Ok){
        action()
    }
    return this
}

fun <E> Response<E>.onFailure(action: () -> Unit): Response<E> {
    if(this.status is Failure) {
        action()
    }
    return this
}
