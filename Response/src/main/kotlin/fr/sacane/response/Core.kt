package fr.sacane.response

import fr.sacane.response.factory.*


fun responseCode(status: Status): Int = when(status) {
    is Created -> 201
    is EmptyCreated -> 201
    is NotFound -> 404
    is BadRequest -> 400
    is Unauthorized -> 401
    is Forbidden -> 403

    //Global response
    is Ok -> 200
    is Error -> 500
}