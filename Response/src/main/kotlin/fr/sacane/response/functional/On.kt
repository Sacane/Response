package fr.sacane.response.functional

import fr.sacane.response.Failure
import fr.sacane.response.Ok
import fr.sacane.response.Response
import fr.sacane.response.status.DefaultStatus

fun <E, S: DefaultStatus> Response<E, S>.onSuccess(action: (E) -> Unit): Response<E, S>{
    if(this.status is Ok && this.value != null){
        action(this.value)
    }
    return this
}

fun <E, S: DefaultStatus> Response<E, S>.onFailure(action: (String) -> Unit): Response<E, S> {
    if(this.status is Failure) {
        action(this.status.message)
    }
    return this
}
