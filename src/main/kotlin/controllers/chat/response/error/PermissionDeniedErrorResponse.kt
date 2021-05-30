package controllers.chat.response.error

import controllers.base.responses.error.BaseErrorResponse

object PermissionDeniedErrorResponse : BaseErrorResponse("You have no rights to edit this message", 403)