# Response 
*A simple library to manage response process as pleased*

This class aims to wrap either a successful response with an embedded value or not, or a failure one.
This Api provide a simple way to handle response from a context and let the 
destination knowing precisely witch error has been occurred.

It also provides a simple way to customize our proper response status, so the engine is not 
simply a binary result.

## Usage

This library provide several default Status such as `Success` and `Failure`.

It also provides several factory function to create it in as simple way: 

```kotlin
fun <E> success(value: E): Response<E, DefaultStatus>
fun success(): EmptyResponse<DefaultStatus>
fun <E> failure(message: String): Response<E, DefaultStatus>
```

Use a response when you want to be clear with the other side of your library or API : 

```kotlin
infix fun Int.divideBy(other: Int): Response<Int, DefaultStatus>
= if(other == 0) failure("Cannot divide by 0") else success(this / other)
```

Now the result is wrapped into its result status response and can be manage in depends on the operation :

```kotlin
val failedResult = (20 divideBy 0) // failure response
val successResult = (10 divideBy 2) // success response

successResult.status.isSuccess // true
failedResult.status.isFailure // true
failedResult.message // Cannot divide by 0
```

## Custom Response Status

As mentioned, response Status can be customized by extending the `Status` type. 

```kotlin
abstract class Status(val isSuccess: Boolean, val isFailure: Boolean) {
    abstract val message: String?
    init {
        require(isSuccess != isFailure) {
            "status cannot be ok and failure at the same time"
        }
    }
}
```

This library provides two different way to handle status response : 
- ``HttpStatus`` witch handle success and failure http Status
- ``ThrowableStatus`` witch handle `Response` with the possibility of manipulating exceptions instead of just knowing the status.

Here the implementation : 

```kotlin
sealed class HttpStatus(isOk: Boolean, isFailure: Boolean, open val code: Int): Status(isOk, isFailure)

open class HttpSuccess (override val code: Int): HttpStatus(true, false, code){
    override val message: String?
        get() = null
}

open class HttpError (override val message: String?, override val code: Int): HttpStatus(false, true, code)
```

With the Http status : 

```kotlin
//Success

class Ok: HttpSuccess(200)
class Created (val url: String): HttpSuccess(201)

// Failures 

class NotFound(override val message: String): HttpError(message, 404)
class BadRequest(override val message: String): HttpError(message, 400)
class Unauthorized(override val message: String): HttpError(message, 401)
class Forbidden(override val message: String): HttpError(message, 403)
class InternalServerError(override val message: String): HttpError(message, 500)
```

## Monad operations 

Response status can also be manipulated through methods that represents operations.