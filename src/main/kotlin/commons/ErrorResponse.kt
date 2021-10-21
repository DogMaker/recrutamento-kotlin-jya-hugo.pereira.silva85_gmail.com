package commons

import domain.exceptions.ConstantsExceptions

class ErrorResponse (
    val type: ConstantsExceptions,
    val message: String
)