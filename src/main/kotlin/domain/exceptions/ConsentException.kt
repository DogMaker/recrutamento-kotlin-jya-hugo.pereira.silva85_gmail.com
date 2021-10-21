package domain.exceptions

import commons.ErrorResponse

abstract class ConsentException: RuntimeException() {
    abstract fun userResponseMessage() : ErrorResponse
    abstract fun statusCode() : Int
}