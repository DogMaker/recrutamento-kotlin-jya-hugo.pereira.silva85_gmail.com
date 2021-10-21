package application.rest.exceptions

import commons.ErrorResponse
import domain.exceptions.ConsentException
import domain.exceptions.ConstantsExceptions
import org.eclipse.jetty.http.HttpStatus

class DeserializationException(override val message: String): ConsentException() {
    override fun userResponseMessage() = ErrorResponse(ConstantsExceptions.DUPLICATED_JTI_EXCEPTION, message)
    override fun statusCode() = HttpStatus.BAD_REQUEST_400
}