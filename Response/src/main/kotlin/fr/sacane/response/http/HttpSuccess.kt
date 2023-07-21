package fr.sacane.response.http

import fr.sacane.response.EmptyOk
import fr.sacane.response.Ok

class Created (val url: String): EmptyOk()
class CreatedWithBody internal constructor(val url: String): Ok()