package domain.exceptions

import commons.ErrorResponse
import org.eclipse.jetty.http.HttpStatus

const val ALREADY_REGISTERED = "There is already a jti registered in the database"

class DuplicateJtiException(override val message: String = ALREADY_REGISTERED): ConsentException() {
    override fun userResponseMessage() = ErrorResponse(
        ConstantsExceptions.DUPLICATED_JTI_EXCEPTION, message)
    override fun statusCode() = HttpStatus.BAD_REQUEST_400
}

