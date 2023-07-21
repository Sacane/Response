# Response 


Response is a simple monad that represents a response from a context. 
This class aims to wrap either a successful response with an embedded value or not, or an error response.
This Api provide a simple way to handle response from a context and let the 
destination knowing witch error has been occurred during the process.

It also provides a simple way to customize our proper status code, so the engine is not 
constraint by only provide Error or Success operation. 

## TODO