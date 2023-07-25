package fr.sacane.response.functional

import fr.sacane.response.Response

fun <E> Response<E>.orElse(defaultValue: E): E = if(this.value == null) defaultValue else value

fun <E> Response<out E>.orElseGet(get: () -> E): E = if(this.value == null) get() else value