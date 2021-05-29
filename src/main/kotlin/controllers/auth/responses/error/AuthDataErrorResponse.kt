package controllers.auth.responses.error

import controllers.base.responses.error.BaseErrorResponse

object AuthDataErrorResponse : BaseErrorResponse("Wrong auth data!", 401)