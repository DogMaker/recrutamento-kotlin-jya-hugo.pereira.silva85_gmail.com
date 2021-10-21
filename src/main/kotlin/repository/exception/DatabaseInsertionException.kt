package repository.exception

import commons.ErrorResponse
import domain.exceptions.ConsentException
import domain.exceptions.ConstantsExceptions
import org.eclipse.jetty.http.HttpStatus

const val MESSAGE_UNKNOWN_EXCEPTION = "There was an error with insert in database"

class DatabaseInsertionException (override val message: String = MESSAGE_UNKNOWN_EXCEPTION): ConsentException() {
    override fun userResponseMessage() = ErrorResponse(
        ConstantsExceptions.DATABASE_INSERT_EXCEPTION, message)
    override fun statusCode() = HttpStatus.BAD_REQUEST_400
}
