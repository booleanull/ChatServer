package controllers.profile.responses.error

import controllers.base.responses.error.BaseErrorResponse

object UserNotFoundErrorResponse : BaseErrorResponse("User with this id doesn't exist!", 404) {
}