package controllers.auth.responses.error

import controllers.base.responses.error.BaseErrorResponse

object UserAlreadyExistsErrorResponse: BaseErrorResponse("User with the same login already exists!", 401)