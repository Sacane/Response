package fr.sacane.response.http


class NotFound(override val message: String): HttpError(message, 404)

class BadRequest(override val message: String): HttpError(message, 400)
class Unauthorized(override val message: String): HttpError(message, 401)

class Forbidden(override val message: String): HttpError(message, 403)

class InternalServerError(override val message: String): HttpError(message, 500)

