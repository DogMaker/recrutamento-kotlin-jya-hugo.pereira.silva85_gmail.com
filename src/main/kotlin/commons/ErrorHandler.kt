package commons

import domain.exceptions.ConsentException
import domain.exceptions.ConstantsExceptions.UNHANDLED_EXCEPTION_THROWN
import io.javalin.http.Context
import org.eclipse.jetty.http.HttpStatus

class ErrorHandler {
    fun handle(exception: Exception, ctx: Context) {
        when (exception) {
            is ConsentException -> {
                val status = exception.statusCode()
                val response = exception.userResponseMessage()

                ctx.json(response)
                ctx.status(status)
            }
            else -> {
                val status = HttpStatus.INTERNAL_SERVER_ERROR_500
                val response = ErrorResponse(UNHANDLED_EXCEPTION_THROWN, "An unhandled exception was thrown" )

                ctx.json(response)
                ctx.status(status)
            }
        }
    }
}
