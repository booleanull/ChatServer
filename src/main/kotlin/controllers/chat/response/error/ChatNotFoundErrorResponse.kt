package controllers.chat.response.error

import controllers.base.responses.error.BaseErrorResponse

object ChatNotFoundErrorResponse: BaseErrorResponse("Chat not found", 403)