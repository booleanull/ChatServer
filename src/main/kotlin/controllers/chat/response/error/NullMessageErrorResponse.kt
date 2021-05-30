package controllers.chat.response.error

import controllers.base.responses.error.BaseErrorResponse

object NullMessageErrorResponse : BaseErrorResponse("Message cannot be null", 400)